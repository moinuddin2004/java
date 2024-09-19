
class abc{
    static int num=9;
   static int num2=10;

    static void show(){
        System.out.println("Hello"+num+num2);
    }
}

class test
{
public static void main(String args[]){
    abc t=new abc();
    System.out.println("Hello World");
    StringBuffer sb=new StringBuffer("Hello");
    System.out.println(sb.append("moin"));
    System.out.println(sb.reverse());
    // t.num=10;
  t.show();
  abc.show();

  Integer num1=9;
  System.out.println(num1);
  
}

}