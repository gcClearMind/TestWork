import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;

public class Rect {
    private BigDecimal length;
    private BigDecimal width;
    Boolean ok;

    public Rect(double length, double width) {
        this.length = BigDecimal.valueOf(length);
        this.width = BigDecimal.valueOf(width);
        if(this.length.compareTo(BigDecimal.valueOf(0)) <= 0 || this.width.compareTo(BigDecimal.valueOf(0)) <= 0) {
            ok = false;
        }
        else {
            ok = true;
        }
    }

    public Rect(int length, int width) {
        this.length = BigDecimal.valueOf(length);
        this.width = BigDecimal.valueOf(width);
        if(length <= 0 || width <= 0) {
            ok = false;
        }
        else {
            ok = true;
        }
    }

    public Rect(long length, long width) {
        this.length = BigDecimal.valueOf(length);
        this.width = BigDecimal.valueOf(width);
        if(length <= 0 || width <= 0) {
            ok = false;
        }
        else {
            ok = true;
        }
    }
    public boolean checkNumber(String a) {
        if(a.charAt(0) == '-' || a.charAt(0) == '0' && a.length() == 1) { //非正数
            return false;
        }
        else if('0' > a.charAt(0) || a.charAt(0) > '9') { //非字符
            return false;
        }
        int tf = 0;
        for(int i = 1; i < a.length(); i++) {
            if(a.charAt(i) == '.') {
                tf++;
                if(tf == 2) {
                    return false;
                }
            }
            else if('0' <= a.charAt(i) && a.charAt(i) <= '9') {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }


    public <T,U>Rect(T length, U width) {
        if(length == null || width == null) {
            ok = false;
        }
        else {
            String a = length.toString();
            String b = width.toString();
            ok = checkNumber(a) && checkNumber(b);
            if(ok == true) {
                this.length = new BigDecimal(a);
                this.width = new BigDecimal(b);
            }
        }
        if(!ok) {
            this.length = null;
            this.width = null;
        }
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = BigDecimal.valueOf(length);
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = BigDecimal.valueOf(width);
    }

    public BigDecimal getArea() {
        if(ok == false) {
            return BigDecimal.valueOf(-1);
        }
        else return length.multiply(width);
    }

    public BigDecimal getPerimeter() {
        if(ok == false) {
            return BigDecimal.valueOf(-1);
        }
        return (length.add(width)).multiply(BigDecimal.valueOf(2));
    }

    public String getObject() {
        if(!ok) return "No legal rectangle";
        return "(" + length + "," + width + ")";
    }

    public static <AnyType>
    AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp) {
        if(arr.length == 0) {
            return (AnyType)(new Rect(0,0));
        }
        
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++)
            if (cmp.compare(arr[i], arr[maxIndex]) > 0)
                maxIndex = i;

        return arr[maxIndex];
    }

    public static class areaCompare implements Comparator<Rect> {
        @Override
        public int compare(Rect o1, Rect o2) {
            // TODO Auto-generated method stub
            if (o1.getArea().compareTo(o2.getArea()) > 0) {
                return 1;
            } else if (o1.getArea() == o2.getArea()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static class perimeterCompare implements Comparator<Rect> {
        @Override
        public int compare(Rect o1, Rect o2) {
            // TODO Auto-generated method stub
            if (o1.getPerimeter().compareTo(o2.getPerimeter()) > 0) {
                return 1;
            } else if (o1.getPerimeter() == o2.getPerimeter()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {

        Rect[] arr = new Rect[]{
                new Rect(10, 20), new Rect(2, 65),
                new Rect(3, 10), new Rect(6, 20)
        };

        System.out.println("面积最大：" + findMax(arr, new areaCompare()).getObject());
        System.out.println("周长最长：" + findMax(arr, new perimeterCompare()).getObject());
    }
}