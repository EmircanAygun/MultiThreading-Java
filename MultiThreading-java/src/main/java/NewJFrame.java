
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewJFrame extends javax.swing.JFrame {
    
    public static int duplicateWord(String str1,String str2) 
    {    
        int count=0;    
        str1 = str1.toLowerCase();    
        str2 = str2.toLowerCase();  
           
        String words[] = str1.split(" ");    
        String words2[] = str2.split(" ");
         
        for(int i = 0; i < words.length; i++) {                  
            for(int j = 0; j < words2.length; j++) {    
                if(words[i].equals(words2[j])) {    
                    count++;}    
            }       
        }
        return count;
    }    
    
    public static int countWords(String str)
    {   
        if (str == null || str.isEmpty())
            return 0;         
        
        String[] words = str.split("\\s+");
        return words.length;
    }
    
    public class WorkerThread2 implements Runnable {  
        private int lineBarrier;
        private int t,p;
        private int sayılanSatır=1;
        private int kolonn;
        private int c1,c2,c3,c4,c5,c6;
        private String filtreDeger;
        private int benzerlikOranı;
        private List<Integer> kolonListesi = new ArrayList<>();
        private int pay=0,payda=0,s1,s2;
        private String bas;
    
        public WorkerThread2(int kolon ,int t,List<Integer> kolonListesi,int benzerlikOranı,String filtreDeger,int line){  
            this.lineBarrier=line;
            this.t=t;
            this.kolonn=kolon;
            this.kolonListesi=kolonListesi;
            this.benzerlikOranı=benzerlikOranı;
            this.filtreDeger=filtreDeger;
    }
    
    public void run() {  
        try {
            
            long startTime = System.nanoTime();

            FileReader filereader = new FileReader("C:/Users/emirc/Desktop/pythonProject/(05k)demo.csv");
            
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            List<String[]> allData = csvReader.readAll();
            
            if(kolonListesi.size()==1){c1=kolonListesi.get(0);}
            if(kolonListesi.size()==2){c1=kolonListesi.get(0);c2=kolonListesi.get(1);}
            if(kolonListesi.size()==3){c1=kolonListesi.get(0);c2=kolonListesi.get(1);c3=kolonListesi.get(2);}
            if(kolonListesi.size()==4){c1=kolonListesi.get(0);c2=kolonListesi.get(1);c3=kolonListesi.get(2);c4=kolonListesi.get(3);}
            if(kolonListesi.size()==5){c1=kolonListesi.get(0);c2=kolonListesi.get(1);c3=kolonListesi.get(2);c4=kolonListesi.get(3);c5=kolonListesi.get(4);}
            if(kolonListesi.size()==6){c1=kolonListesi.get(0);c2=kolonListesi.get(1);c3=kolonListesi.get(2);c4=kolonListesi.get(3);c5=kolonListesi.get(4);c6=kolonListesi.get(5);}          
            
            for (String[] row : allData) {
                if(sayılanSatır == lineBarrier){                                           
                        p=1;
                        
                        for (String[] row2 : allData){                            
                                
                            s1=countWords(row[kolonn-1]);
                            s2=countWords(row2[kolonn-1]);
                            if(s1>s2){payda=s1;}
                            else     {payda=s2;}
                                
                            pay=duplicateWord(row[kolonn-1],row2[kolonn-1]);
                                                            
                            if(!filtreDeger.contentEquals("-") && jComboBox3.getSelectedIndex()==6){
                            if(((pay*100)/payda)>=benzerlikOranı && row[jComboBox2.getSelectedIndex()].contentEquals(filtreDeger) && p>lineBarrier){
                                System.out.println(row[jComboBox2.getSelectedIndex()]);
                                if(kolonListesi.size()==1){bas=(row[c1]+" -- "+row2[c1]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==2){bas=(row[c1]+" , "+row[c2]+" -- "+row2[c1]+" , "+row2[c2]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==3){bas=(row[c1]+" ,"+row[c2]+" , "+row[c3]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==4){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==5){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==6){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" , "+row[c6]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]+" , "+row2[c6]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                            }
                            }
                            else if(jComboBox3.getSelectedIndex()!=6 && filtreDeger.contentEquals("-")){
                            if(((pay*100)/payda)>=benzerlikOranı && row[jComboBox3.getSelectedIndex()].contentEquals(row2[jComboBox3.getSelectedIndex()]) && p>lineBarrier){
                                //System.out.println(row[jComboBox2.getSelectedIndex()]);
                                if(kolonListesi.size()==1){bas=(row[c1]+" -- "+row2[c1]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==2){bas=(row[c1]+" , "+row[c2]+" -- "+row2[c1]+" , "+row2[c2]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==3){bas=(row[c1]+" ,"+row[c2]+" , "+row[c3]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==4){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==5){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==6){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" , "+row[c6]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]+" , "+row2[c6]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                            }
                            }
                            else if(jComboBox3.getSelectedIndex()!=6 && !filtreDeger.contentEquals("-")){
                            if(((pay*100)/payda)>=benzerlikOranı && row[jComboBox3.getSelectedIndex()].contentEquals(row2[jComboBox3.getSelectedIndex()]) && row[jComboBox2.getSelectedIndex()].contentEquals(filtreDeger) && p>lineBarrier){
                                //System.out.println(row[jComboBox2.getSelectedIndex()]);
                                if(kolonListesi.size()==1){bas=(row[c1]+" -- "+row2[c1]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==2){bas=(row[c1]+" , "+row[c2]+" -- "+row2[c1]+" , "+row2[c2]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==3){bas=(row[c1]+" ,"+row[c2]+" , "+row[c3]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==4){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==5){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==6){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" , "+row[c6]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]+" , "+row2[c6]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                            }
                            }
                            else{
                            if(((pay*100)/payda)>=benzerlikOranı && p>lineBarrier){
                                if(kolonListesi.size()==1){bas=(row[c1]+" -- "+row2[c1]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==2){bas=(row[c1]+" , "+row[c2]+" -- "+row2[c1]+" , "+row2[c2]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==3){bas=(row[c1]+" ,"+row[c2]+" , "+row[c3]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==4){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==5){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                                if(kolonListesi.size()==6){bas=(row[c1]+" , "+row[c2]+" , "+row[c3]+" , "+row[c4]+" , "+row[c5]+" , "+row[c6]+" -- "+row2[c1]+" , "+row2[c2]+" , "+row2[c3]+" , "+row2[c4]+" , "+row2[c5]+" , "+row2[c6]);
                                    jTextArea1.append(bas+"\n");System.out.print(pay+"/"+payda+" "+((100*pay)/payda)+" ");System.out.println(benzerlikOranı);}
                            }
                            }
                        p++;
                        }                    
                lineBarrier=lineBarrier + t;
                }
            sayılanSatır++;    
            }
            long time = System.nanoTime() - startTime;
            jTextArea2.append(Thread.currentThread().getName()+" : "+time+"\n");
            toplamTime=toplamTime+time;
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        //System.out.println(Thread.currentThread().getName());  
        System.out.println(Thread.currentThread().getName()+" (Bitti)");  
    }  
    
} 
    
    public NewJFrame() {
        initComponents();
    }
    private int benzerlikOranı;
    private int kolon;
    private int threadSayısı=1;
    private String filtreDeğer;
    public long toplamTime=0;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCheckBox1.setText("Product");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Issue");

        jCheckBox3.setText("Company");

        jCheckBox4.setText("State");

        jCheckBox5.setText("ZIP Code");

        jCheckBox6.setText("Complaint ID");

        jLabel1.setText("Benzerlik Oranı (%) :");

        jLabel2.setText("Araştırılacak Sütun :");

        jButton1.setText("BAŞLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Thread Sayısı :");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product", "Issue", "Company", "State", "ZIP Code", "Complaint ID" }));

        jButton2.setText("TEMİZLE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product", "Issue", "Company", "State", "ZIP Code", "Complaint ID", "-" }));
        jComboBox2.setSelectedIndex(6);

        jTextField2.setText("-");

        jLabel4.setText("Threadler         | ");

        jLabel5.setText("Çalışma Zamanları ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product", "Issue", "Company", "State", "ZIP Code", "Complaint ID", "-" }));
        jComboBox3.setSelectedIndex(6);

        jLabel6.setText("Eşitlik Aranacak Sütunlar : ");

        jLabel7.setText("Sütun Değeri :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jTextField1))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<Integer> kolonListesi = new ArrayList<>();

        if(jCheckBox1.isSelected()){kolonListesi.add(0);}
        if(jCheckBox2.isSelected()){kolonListesi.add(1);}
        if(jCheckBox3.isSelected()){kolonListesi.add(2);}
        if(jCheckBox4.isSelected()){kolonListesi.add(3);}
        if(jCheckBox5.isSelected()){kolonListesi.add(4);}
        if(jCheckBox6.isSelected()){kolonListesi.add(5);}
        
        try{
        benzerlikOranı = Integer.parseInt(jTextField1.getText().trim());}
        catch(Exception e){e.printStackTrace();}
        
        kolon = jComboBox1.getSelectedIndex()+1;
        
        try{
        filtreDeğer = jTextField2.getText();}
        catch(Exception e){e.printStackTrace();}
        
        try{
        threadSayısı = Integer.parseInt(jTextField3.getText().trim());
        }catch(Exception e){e.printStackTrace();}
        
        
        ExecutorService executor = Executors.newFixedThreadPool(threadSayısı);  
        for (int line = 1; line <= threadSayısı; line++) {  
            Runnable worker = new WorkerThread2(kolon,threadSayısı,kolonListesi,benzerlikOranı,filtreDeğer,line);  
            executor.execute(worker); 
        }  
        executor.shutdown();   
        while (!executor.isTerminated()) {   }  
        
        jTextArea2.append("Toplam Çalışma Zamanı : "+toplamTime);
        
        System.out.println("threadler bitti"); 
        long heapsize = Runtime.getRuntime().totalMemory();
        System.out.println("heapsize is :: " + heapsize);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
        jTextArea2.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
