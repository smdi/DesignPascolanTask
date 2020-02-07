package aidev.com.androiddesigns;

public class Initialiser {


    public String data1;


    public String image;

    public Initialiser(){

    }

    public Initialiser(String data1,String image) {
        this.data1 = data1;
        this.image = image;
    }

    public String getData1() {
        return data1;
    }
    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
