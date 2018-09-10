// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:59:16
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MesscatPage.java

package cc.modules.commons;

import java.util.ArrayList;
import java.util.List;

public class MesscatPage
{

    public MesscatPage()
    {
    }

    public static List splitPageNews(List objectList, int perPageNum)
        throws Exception
    {
        if(objectList == null)
            return null;
        List enList = new ArrayList();
        int pageNum = objectList.size() / perPageNum;
        int num = objectList.size() % perPageNum;
        if(pageNum < 1)
        {
            List resultList = objectList.subList(0, objectList.size());
            enList.add(resultList);
        } else
        {
            int i = 0;
            int j = 0;
            i = 0;
            for(j = 0; i < pageNum; j += perPageNum)
            {
                List temp = objectList.subList(j, j + perPageNum);
                enList.add(i, temp);
                i++;
            }

            if(num != 0)
            {
                List temp = objectList.subList(j, j + num);
                enList.add(i, temp);
            }
        }
        return enList;
    }

}