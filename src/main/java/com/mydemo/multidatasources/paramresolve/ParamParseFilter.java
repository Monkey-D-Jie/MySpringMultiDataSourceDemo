package com.mydemo.multidatasources.paramresolve;

import com.alibaba.fastjson.JSONObject;
import com.mydemo.multidatasources.entity.ResponseBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一参数封装过滤器
 */
public class ParamParseFilter implements Filter {

    public static RedisTemplate redisTemplate;

    // 用于创建MultipartHttpServletRequest
    private MultipartResolver multipartResolver = new CommonsMultipartResolver();

    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-*.xml");
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
    }

    @Override
    public void destroy() {

    }

    /**
     * 检查url和session状况
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.contains("/具体的业务请求url")) {
//            String enctype = request.getContentType();
//            if (!StringUtils.isEmpty(enctype) && enctype.contains("multipart/form-data")) {
//                MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(req);
//                //将转化后的reuqest赋值到过滤链中的参数
//                req = multiReq;
//            }
            chain.doFilter(req, resp);
            return;
        }
        if (!StringUtils.isEmpty(uri) && !uri.endsWith("login.do") && !uri.endsWith("doUploadPicture.do")) {
            //解析post的json参数
            String body = getBody(req);
            Map paramMap = JSONObject.parseObject(body);
            if (paramMap != null && !paramMap.isEmpty()) {
                Object param = paramMap.get("data");
                String data = null;
                if (param instanceof String && !StringUtils.isEmpty(param)) {
                    data = (String) param;
                }
                Map<String, String[]> newParam = new HashMap<String, String[]>();
//                String[] dsCode = new String[1];
//                dsCode[0] = (String)paramMap.get("dsCode");
                String[] token = new String[1];
                token[0] = (String) paramMap.get("token");
//                newParam.put("dsCode",dsCode);
                newParam.put("token", token);
                //检测登录
                if (!uri.contains("/login/")){
                    String key = "redis中缓存用的key";
                    if (!redisTemplate.hasKey(key)) {
                        resp.setContentType("application/json; charset=utf-8");
                        resp.setCharacterEncoding("UTF-8");
                        resp.addHeader("Access-Control-Allow-Origin", "*");
                        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
                        String result = JSONObject.toJSON(new ResponseBean(new Exception("登录超时，请重新登录!"), "-2")).toString();
                        PrintWriter out = resp.getWriter();
                        out.append(result);
                        out.flush();
                        out.close();
                        return;
                    }
                }
                //使用解析数据重新生成ServletRequest，供doChain调用
                request = getRequest(req, data, newParam);
            }
            chain.doFilter(request, resp);
        } else if (!StringUtils.isEmpty(uri) && !uri.endsWith("login.do")) {
            chain.doFilter(request, resp);
        } else if (!StringUtils.isEmpty(uri) && !uri.endsWith("doUploadPicture.do")) {
            chain.doFilter(request, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * 获取请求体方法
     * @param request
     * @return
     * @throws IOException
     */
    private String getBody(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[1024];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (null != bufferedReader) {
                bufferedReader.close();
            }
        }
        body = stringBuilder.toString();
        return body;
    }

    /**
     * 将post解析过后的request进行封装改写
     *
     * @param request
     * @param body
     * @return
     */
    private ServletRequest getRequest(ServletRequest request, String body, Map<String, String[]> newParams) {

        String enctype = request.getContentType();

        if ((!StringUtils.isEmpty(enctype)) && enctype.contains("application/json")) {
            return new PostServletRequest((HttpServletRequest) request, body, newParams);
        }
        return request;
    }
}
