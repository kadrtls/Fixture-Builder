import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fixture {
    ArrayList<String> clubs=new ArrayList<>();
    ArrayList<String> coupleTeams=new ArrayList<>();
    Scanner input=new Scanner(System.in);
    Random random=new Random();

    public void start(){
        boolean entered=true;
        while (entered){
            System.out.println("1-Takım ekleme");
            System.out.println("2-Fikstur çek");
            System.out.println("3-Çıkış yap");
            System.out.print("Yukarıdaki işlemlerden birini seçiniz:");
            int selectedCase=input.nextInt();
            switch (selectedCase){
                case 1:
                    System.out.print("Eklenecek takım adını giriniz:");
                    input.nextLine();
                    String clubName=input.nextLine();
                    clubs.add(clubName);
                    break;
                case 2:
                    fixture();
                    break;
                case 3:
                    entered=false;
                    break;
                default:
                    System.out.println("Geçerli bir değer giriniz");
            }
        }
    }
    //Takım sayısı tek sayı ise Bay eklenir
    public void fixture(){
        if (clubs.size()%2==1){
            clubs.add("Bay");
        }
        for (int a=0;a<clubs.size()-1;a++){
            String homeTeam,awayTeam;
            int matchTeams=0;
            ArrayList<String> selectedTeams=new ArrayList<>();
            while (matchTeams<clubs.size()/2){
                homeTeam= clubs.get(random.nextInt(clubs.size()));
                awayTeam= clubs.get(random.nextInt(clubs.size()));
                if (!awayTeam.equals(homeTeam)){
                    if (!selectedTeams.contains(homeTeam) && !selectedTeams.contains(awayTeam)){
                        String match1=(homeTeam +" vs "+ awayTeam);
                        String match2=(awayTeam +" vs "+ homeTeam);
                        if (!coupleTeams.contains(match1) && !coupleTeams.contains(match2)){
                            coupleTeams.add(match1);
                            coupleTeams.add(match2);

                            selectedTeams.add(homeTeam);
                            selectedTeams.add(awayTeam);

                            matchTeams++;
                        }
                    }
                }
            }
        }
        printTeams();
        //selectedTeams.clear();
    }
    public void printTeams(){
        int tour=(clubs.size()-1)*2;
        String[] firstTeams = new String[coupleTeams.size()/2];
        String[] secondTeams = new String[coupleTeams.size()/2];

        for (int j=0;j<coupleTeams.size()/2;j++){
            firstTeams[j]=coupleTeams.get(j*2);
        }
        for(int y=0;y<coupleTeams.size()/2;y++){
            secondTeams[y]=coupleTeams.get(y*2+1);
        }
        //ilk yapılacak maçlar
        for (int i=1;i<=tour/2;i++){
            System.out.println("Round "+i);
            for (int j=0;j<i*2;j++){
                if ((i-1)*2<=j && j<i*2){
                    System.out.println(firstTeams[j]);
                }
            }
            System.out.println("------------------------");
        }
        //Rövanş maçları
        for (int i=1;i<=tour/2;i++){
            System.out.println("Round "+(i+ clubs.size()/2));
            for (int j=0;j<i*2;j++){
                if ((i-1)*2<=j && j<i*2)
                    System.out.println(secondTeams[j]);
            }
            System.out.println("------------------------");
        }
    }
}
