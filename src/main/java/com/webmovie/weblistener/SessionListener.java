package com.webmovie.weblistener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {


    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Integer n = (Integer)se.getSession().getServletContext().getAttribute("countUsers");
        ++n;
        // Dọc dữ liệu ra - đi 1 user sau đó đưa lại vào file//
        se.getSession().getServletContext().setAttribute("countUsers", n);
    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se)  {
        HttpSession session = se.getSession();
        Integer n = (Integer)session.getServletContext().getAttribute("countUsers");
        n--;
        // Dọc dữ liệu ra - đi 1 user sau đó đưa lại vào file//
        Integer dem = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\text.txt"));
            String line;
            while((line = reader.readLine())!= null){
                dem = Integer.valueOf(line);
            }
            ++dem;
            reader.close();
        } catch (IOException e) {
        }

        // Ghi lại thông tin vào file file//
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\text.txt"));
            writer.write(dem+"");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.getServletContext().setAttribute("countUsers", n);
    }

}
