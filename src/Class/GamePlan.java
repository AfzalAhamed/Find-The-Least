
package Class;

public class GamePlan {
    int numList[];
    int timer=0;
    int chance=0;
    int scoreLimit=0;
    int btncount=0;

    public int getTimer() {
        return timer;
    }

    public int getChance() {
        return chance;
    }

    public int[] getNumList() {
        return numList;
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public int getBtncount() {
        return btncount;
    }
    
    
    //basic game plan
    public void gamePlan(int level)
    {
        if(level<=7){
            generateNumbers(8,1,99);
            timer = 20;
            chance = 2;
            scoreLimit=100;
            btncount=8;
         }else if(level>7 && level<=14){
            generateNumbers(18,100,990);
            timer = 45;
            chance = 3;
            scoreLimit=240;
            btncount=18;
         }else if(level>14 && level<=20){
            generateNumbers(30,1000,9999);
            timer = 60;
            chance = 4;
            scoreLimit=360;
            btncount=30;
         }
    }
    //genarate numberlist
    public void generateNumbers(int size, int min, int max)
    {
         
        numList = new int[size];
        int x=0;
        int num;
        while(x<size){
           num=(int)(Math.random()*(max-min+1)+min);
            for(int y=0;y<size;y++){
                if(num==numList[y])
                {
                   num=(int)(Math.random()*(max-min+1)+min);
                   y=0;
                }
            } 
            num=(int)(Math.random()*(max-min+1)+min);
            for(int y=0;y<size;y++){
                if(num==numList[y])
                {
                   num=(int)(Math.random()*(max-min+1)+min);
                   y=0;
                }
            }
            numList[x]=num ;
            x++;

        }
    }
    //when a button clicked
    public boolean clicked(int value){
        boolean val=false;
        int size = numList.length;
        
        //array sort
        int temp;  
        for (int i = 0; i < size; i++)   
        {  
            for (int j = i + 1; j < size; j++)   
            {  
                if (numList[i] > numList[j])   
                {  
                    temp = numList[i];  
                    numList[i] = numList[j];  
                    numList[j] = temp;  
                }  
            }  
        }
        
        //check the smallest
        int small=numList[1];
        int index=-1;
        
        for(int i = 0; i<size; i++ )
        {
            if(numList[i]<small && numList[i]!=0)
            {
                small=numList[i];
                index=i;
            }
        }
        
        if(small==value)
        {
              val=true;
              numList[index]=99999;
        }
        return val;
    }
    
    //check all clicked
    public boolean isAllClicked(){
        int size = numList.length;
        for(int i = size-1; i>=0; i-- )
        {
            if(numList[i]!=99999)
            {
                return false;
            }
        }
        return true;
    }
    
    //set score on score board
    public int setScore(int score){
        score=score+20;
        return score;
    }
}
