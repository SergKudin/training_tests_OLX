package utils;

public class ResultSearch {

    private String NameLot;
    private Integer Price;

    public ResultSearch(String NameLot, String Price) {
        String Pr = Price;
        Pr = Pr.replaceAll("[^\\d]", "");
        this.NameLot = NameLot.replaceAll(";",",");
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

    public Integer getPrice() {
        return Price;
    }

}