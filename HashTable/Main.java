
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
public class Main{


    static int files ;
    
    static ArrayList<ArrayList<Integer>> length;
    static int  n ; 
    static int [] traversiers ;
    static int  bestK; 
    public static int [] currX ; 
    public static int [] bestX ; 
    static HashMap<Integer,Boolean> isVisited ;
    static  int newS;
    static int index ;
    public static void initialize() {
        bestK= -1;

       
            Scanner scanner = new Scanner(System.in);
       
            files = Integer.parseInt(scanner.nextLine()); 
            length = new ArrayList<ArrayList<Integer>>(files);
            traversiers=new int[files];
            for(int i=0;i<files;i++){
                scanner.nextLine();
                traversiers[i]=Integer.parseInt(scanner.nextLine())*10*10;
                int a = Integer.parseInt(scanner.nextLine());
                length.add(new ArrayList<Integer>());               
                 while(a!=0){
                    length.get(i).add(a);
                    a=Integer.parseInt(scanner.nextLine());
                }

            }
         
          

         
   
            scanner.close();
         
        
       
    }

    public static void BacktrackSlove(int currK,int currS){
    	
         int longeur_voitures =0 ;
           for(int i=0;i<currK;i++){
            longeur_voitures=longeur_voitures+length.get(index).get(i);
           }
        if (currK>bestK  ){
            bestK = currK ; 
            for(int i=0 ; i<currX.length;i++){
                bestX[i]=currX[i];    
                }                 
            }
        if(currK<n){
            if((currS-length.get(index).get(currK))>=0&& (isVisited.get(hashCode(currK,currS))==null)){
                currX[currK]=1; 
               newS=currS-length.get(index).get(currK);
                
               BacktrackSlove(currK+1, newS);
               isVisited.put(hashCode(currK,newS),true); 
            }

            int espace_libre = ((2*traversiers[index])-longeur_voitures-currS)-length.get(index).get(currK);
            if( espace_libre>=0&& (isVisited.get(hashCode(currK,currS))==null) ){
                currX[currK]=0;
      		 BacktrackSlove(currK+1, currS);
            isVisited.put(hashCode(currK,currS),true) ;   
            }
        }
        
    }

  
    public static int hashCode(int k, int s){
        
            
     for(int i =0;i<k;i++){
            if(i%3==0){
                s++;
            }else if(i%3==1){
                s+=10;
            }
            else{
                s+=100;
            }
        }
        return s;
     }

     
    public static void main(String[] args) { 


        initialize();
        for(int cas=0;cas<files;cas++){
            index=cas;
            n= length.get(cas).size();
            bestX= new int[n+1];
            currX=new int [n+1];
            bestK=-1;
            for(int i=0;i<bestX.length;i++ ){
                bestX[i]=-1;
                currX[i]=-1;
            }
            int add = traversiers[cas]/10;
            isVisited = new HashMap<>(traversiers[cas]+100*n); 
        

            BacktrackSlove(0, traversiers[cas]);
            String a = "starboard";
        	String b = "port";

            int cars=0 ;

            for(int i=0;i<bestX.length;i++){
                if(bestX[i]==0 || bestX[i]==1){
                    cars++;}
                
            }
            System.out.println(cars);



        for(int i=0;i<bestX.length;i++){
            if(bestX[i]==1)
            { System.out.println(a);}
            else if (bestX[i]==0) 
             { System.out.println(b);}

        }
        if(cas<files-1)
        System.out.println("");

    	


        }



     }

     


        

    }
