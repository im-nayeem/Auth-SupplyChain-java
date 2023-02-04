package com.example.my_web_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 02-Feb-23
 *
 * @author Nayeem
 */
class Url{
    private String label,url;
    public Url(String label,String url)
    {
        this.label=label;
        this.url=url;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

}
class Section{
    private String label;
    private List<Url> url;
    public Section(String label)
    {
        this.label=label;
    }
    public String getLabel() {
        return label;
    }
    public List<Url> getUrl() {
        return url;
    }

}
public class Check {
    public static void main(String[] args) {
        List<Section> sections = new ArrayList<>();

        String code="AP";
        String URL="https://domain.com";
        int n=10,start=1;
        for (int i = 1; i <= n; i++) {
            Section section = new Section(code+start);

            List<Url> urls = new ArrayList<>();
            Url url = new Url("Customer",URL+"/customer/verify?p_id="+section.getLabel());
            urls.add(url);
            url=new Url("Customer",URL+"/seller/sell?p_id="+section.getLabel());
            urls.add(url);

            sections.add(section);
        }


    }
}
