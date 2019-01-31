package com.mydemo.multidatasources.paramresolve;

import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * 用于替换本身的servletRequest对象的类
 */
public class PostServletRequest extends HttpServletRequestWrapper {
    private String body=null;
    private Map<String, String[]> params;
    public PostServletRequest(HttpServletRequest request,String body,Map<String,String[]> newParams) {
        super(request);
        this.body=body;
        this.params = newParams;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream inputStream = null;
        if(!StringUtils.isEmpty(body)){
            inputStream =  new PostServletInputStream(body);
        }
        return inputStream;
    }


    @Override
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if(enc == null) {enc = "UTF-8";};

        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {
        return params;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Enumeration getParameterNames()
    {
        Vector l = new Vector(params.keySet());
        return l.elements();
    }

    @Override
    public String[] getParameterValues(String name)
    {
        Object v = params.get(name);
        if (v == null)
        {
            return null;
        } else if (v instanceof String[])
        {
            return (String[]) v;
        } else if (v instanceof String)
        {
            return new String[] { (String) v };
        } else
        {
            return new String[] { v.toString() };
        }
    }

    @Override
    public String getParameter(String name)
    {
        Object v = params.get(name);
        if (v == null)
        {
            return null;
        } else if (v instanceof String[])
        {
            String[] strArr = (String[]) v;
            if (strArr.length > 0)
            {
                return strArr[0];
            } else
            {
                return null;
            }
        } else if (v instanceof String)
        {
            return (String) v;
        } else
        {
            return v.toString();
        }
    }
}
