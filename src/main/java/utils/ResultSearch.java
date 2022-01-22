package utils;

public class ResultSearch {

    private String NameLot;
    private Integer Price;
    private String Link;

    public ResultSearch(String NameLot, String Price, String Link) {
        String Pr = Price;
        Pr = Pr.replaceAll("[^\\d]", "");
        this.NameLot = NameLot.replaceAll(";",",");
        this.Link = Link;
        try {
            this.Price = Integer.parseInt(Pr);
        }
        catch (Exception e) {
            this.Price = 0;
//            e.printStackTrace();
        }
    }

    public String getNameLot() {
        return NameLot;
    }

    public String getLink() {return Link;}

    public Integer getPrice() {
        return Price;
    }

}