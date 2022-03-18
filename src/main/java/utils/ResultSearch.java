package utils;

import java.util.ArrayList;

public class ResultSearch extends ArrayList {

    private String NameLot;
    private Integer Price;
    private String Link;

    public ResultSearch(String NameLot, String Price, String Link) {
        String Pr = Price;
        Pr = Pr.replaceAll("[^\\d]", "");
        this.NameLot = NameLot.replaceAll(";", ",");
        this.Link = Link;
        try {
            this.Price = Integer.parseInt(Pr);
        } catch (Exception e) {
            this.Price = 0;
//            e.printStackTrace();
        }
    }

    public ResultSearch(String NameLot, Integer Price, String Link) {
        this.NameLot = NameLot;
        this.Link = Link;
        this.Price = Price;
    }

    public String getNameLot() {
        return NameLot;
    }

    public String getLink() {
        return Link;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setNameLot(String NameLot) {
        this.NameLot = NameLot;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }
}