import java.util.*;
import java.io.*;

class Main{
  public static String readInput(String input)
  {
   try
   {
     int a;
     FileInputStream inp = new FileInputStream("abc.c");
     while((a = inp.read()) != -1){
       input += (char)a;
     }
     inp.close();
   }
   catch(Exception e)
   {
     System.out.println("Error while reading");
   }
   return input;
 }

 public static void varLit(String lex, ArrayList symbol, ArrayList literal){
   char[] lexchar = lex.toCharArray();
   int charflag = 0;
   int numflag = 0;
   int err = 0;
   int var = 0;
   int lit = 0;
   if(lexchar[0]>='a' && lexchar[0]<='z')
   {
     charflag = 1;
     var = 1;
     for(int i=0;i<lexchar.length;i++)
     {
       if(lexchar[i]>=0 && lexchar[i]<=9)
       {
         numflag = 1;
       }
       if(!(lexchar[i]>='a' && lexchar[i]<='z' && numflag == 0)){
         err =1;
         break;
       }
     }
   }

   if(lexchar[0]>='0' && lexchar[0]<='9'){
     numflag = 1;
     lit = 1;
     for(int i=0;i<lexchar.length;i++){
       if(lexchar[i]>='a' && lexchar[i]<='z'){
         err = 1;
         break;
       }
     }
   }

   if(err != 1){
     if(lit == 1){
       if(!literal.contains(lex)){
         literal.add(lex);
       }
       System.out.print("<Literal" + literal.indexOf(lex) + "> ");
     }

     if(var == 1){
       if(!symbol.contains(lex)){
         symbol.add(lex);
       }
       System.out.print("<Variable#" +symbol.indexOf(lex) + "> ");
     }
   }
   else{
     System.out.print("ERROR ");
   }

 }

 public static void main(String args[]){
   String incode = "";
   incode = readInput(incode);
   String[] inputline = incode.split("[\\r\\n]");

   ArrayList<String> keyword = new ArrayList<>
   (Arrays.asList("include", "void", "int", "char", "for", "while", "break", "do", "long", "double", "float", "continue", "short", "case", "default", "if", "else"));
   ArrayList<String> predefined = new ArrayList<>(Arrays.asList("main", "printf", "scanf", "return"));
   ArrayList<String> special = new ArrayList<>(Arrays.asList("#", "<", ">", "(", ")", "{", "}", "[", "]", ",", "%", ";"));
   ArrayList<String> operator = new ArrayList<>(Arrays.asList("+", "-", "/", "*", "="));
   ArrayList<String> header = new ArrayList<>(Arrays.asList("conio.h", "stdio.h"));
   ArrayList<String> literal = new ArrayList<>();
   ArrayList<String> symbol = new ArrayList<>();
   ArrayList<String> strconst = new ArrayList<>();


   for(int i=0;i<inputline.length;i++)//parsing till end of 1st line
   {
     System.out.println(inputline[i]);
     StringTokenizer strtoken = new StringTokenizer(inputline[i]," =,(){}#<>;",true);//delimiters
     while(strtoken.hasMoreTokens())
     {
       String lex = strtoken.nextToken();
       if(!lex.equals(" "))
       {
         // System.out.print(lex);
         if(special.contains(lex))
         {
           // System.out.println(lex);
           System.out.print("<SpecialSym>");
         }
         else if(keyword.contains(lex))
         {
           // System.out.println(lex);
           System.out.print("<Keyword>");
         }
         else if(header.contains(lex))
         {
           // System.out.println(lex);
           System.out.print("<Header>");
         }
         else if(operator.contains(lex))
         {
           // System.out.println(lex);
           System.out.print("<Operator>");
         }
         else if(predefined.contains(lex))
         {
           // System.out.println(lex);
           System.out.print("<PredefinedFunc>");
           if(lex.equals("printf"))
           {
             lex = strtoken.nextToken();
             System.out.print("<SpecialSym>");
             String[] temp = inputline[i].split("\"");
             strconst.add(temp[1]);
             System.out.print("<StrConstant>");
             

           }
         }        
         else
         {
           varLit(lex, symbol, literal);
         }

       }
      

     }
     System.out.println();
      
   }
 }
}
