import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


class PetSimGUI{
  //class fields and variables, mostly GUI related
  
   private final GridBagConstraints gbc;
   private String user, pass;
  
  //dimensions of the window
   private final static int LENGTH = 700;
   private final static int WIDTH = 500;
 
   private int i;
  
  //the speed that the pet's stats decrease in
   private final static int REFRESH_RATE = 4000;
   private final static int HUNGER_DECAY_RATE = 500;
   private final static int HYGIENE_DECAY_RATE = 1500;
   private final static int ENERGY_DECAY_RATE = 5000;
   private final static int HIGHEST_DECAY_RATE = 500;
  
  //the game's main frame. everything will be in here
   private JFrame frame = new JFrame("Pet Simulator");
  
   private static UserDatabase uDatabase;
   
  //the pet's status bars
   private JProgressBar hungerBar = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
   private JProgressBar hygieneBar = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
   private JProgressBar energyBar = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
  
  //timer for refreshing the GUI of the pet
   private Timer refreshTimer;
  
  //all the panels that needs to be used in order for the program to function. Layout managers are hard.
   private JPanel userPanel = new JPanel();
   private JPanel namePanel = new JPanel();
   private JPanel passPanel = new JPanel();
   private JPanel passAgainPanel = new JPanel();
   private JPanel mainPanel = new JPanel();
   private JPanel buttPanel = new JPanel();
   private JPanel loginPanel = new JPanel();
   private JPanel hungerPanel = new JPanel();
   private JPanel hygienePanel = new JPanel();
   private JPanel energyPanel = new JPanel();
   private JPanel statusPanel = new JPanel();
   private JPanel controlPanel = new JPanel();
   private JPanel petPanel = new JPanel();
   private JPanel goldPanel = new JPanel();
   private JPanel levelPanel = new JPanel();
   private JPanel selectPanel[] = new JPanel[3];
   private JPanel bigPPanel = new JPanel();
  
  //dimension of the screen
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
  
  //buttons that will be in the game
   private JButton loginButt = new JButton("Login");
   private JButton newAcc = new JButton("New Account");
   private JButton createAcc = new JButton("Create Account");
   private JButton backButt = new JButton("Back");
   private JButton feedButt = new JButton("Feed");
   private JButton washButt = new JButton("Bath");
   private JButton sleepButt = new JButton("Sleep");
   private JButton miniGamesButt = new JButton("Minigames");
   private JButton rankingButt = new JButton("Ranking");
   private JButton choiceOneButt = new JButton("Choose");
   private JButton choiceTwoButt = new JButton("Choose");
   private JButton choiceThreeButt = new JButton("Choose");
   private JButton buyPetButt = new JButton("Buy Other Pets");
  
  //text fields for purposes such as logging in
   private final JTextField userName = new JTextField("User Name", 15);
   private final JPasswordField password = new JPasswordField("Password", 15);
   private final JTextField newUserTextField = new JTextField("",15);
   private final JPasswordField newPassField = new JPasswordField("",15);
   private final JPasswordField newPassAgainField = new JPasswordField("",15);
  
  //text blocks that will be used to help the user understand the game better
   private JLabel newUserName = new JLabel("User name:           ");
   private JLabel newPassword = new JLabel("Password:             ");
   private JLabel newPassAgain = new JLabel("Reenter password: ");
   private JLabel hungerLabel = new JLabel("Hunger");
   private JLabel hygieneLabel = new JLabel("Hygiene");
   private JLabel energyLabel = new JLabel("Energy");
   private JLabel goldHeaderLabel = new JLabel("Gold: ");
   private JLabel goldLabel;
   private JLabel levelHeaderLabel = new JLabel("Player Level: ");
   private JLabel levelLabel = new JLabel("0");
   private JLabel petNameLabel = new JLabel();

  //images for previewing our cute pets
   private ImageIcon cat1 = new ImageIcon(getClass().getResource("cat1.png"));
   private ImageIcon cat2 = new ImageIcon(getClass().getResource("cat2.png"));
   private ImageIcon cat3 = new ImageIcon(getClass().getResource("cat3.png"));
  
  //GUI's constructor
   public PetSimGUI(){
      createLoginScreen();
   }
  
   {
      gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1.0;
      gbc.weighty = 1.0;
      gbc.fill = GridBagConstraints.BOTH;
      gbc.anchor = GridBagConstraints.NORTHWEST;
   }
  
  
   public JPanel wrapInBackgroundImage(JComponent component,
                                      Icon backgroundIcon) {
      return wrapInBackgroundImage(
                                 component,
                                 backgroundIcon,
                                 JLabel.TOP,
                                 JLabel.LEADING);
   }
  
  
   public JPanel wrapInBackgroundImage(JComponent component,
                                      Icon backgroundIcon,
                                      int verticalAlignment,
                                      int horizontalAlignment) {
    
    // make the passed in swing component transparent
      component.setOpaque(false);
    
    // create wrapper JPanel
      JPanel backgroundPanel = new JPanel(new GridBagLayout());
    
    // add the passed in swing component first to ensure that it is in front
      backgroundPanel.add(component, gbc);
    
    // create a label to paint the background image
      JLabel backgroundImage = new JLabel(backgroundIcon);
    
    // set minimum and preferred sizes so that the size of the image
    // does not affect the layout size
      backgroundImage.setPreferredSize(new Dimension(1,1));
      backgroundImage.setMinimumSize(new Dimension(1,1));
    
    // align the image as specified.
      backgroundImage.setVerticalAlignment(verticalAlignment);
      backgroundImage.setHorizontalAlignment(horizontalAlignment);
    
    // add the background label
      backgroundPanel.add(backgroundImage, gbc);
    
    // return the wrapper
      return backgroundPanel;
   }


   void createLoginScreen(){
    //these listners are for the user to press the enter key or the login button to login to the game
      password.setFocusable(true);
      userName.setFocusable(true);
      userName.addKeyListener(
            new KeyListener() {
               public void keyPressed(KeyEvent e) {
                  if(e.getKeyCode() == KeyEvent.VK_ENTER){
                     user = userName.getText();
                     pass = new String(password.getPassword());
                     login(user,pass);
                  }
               }
            
               public void keyReleased(KeyEvent e) {  }
            
               public void keyTyped(KeyEvent e) { }
            }); 
      password.addKeyListener(
            new KeyListener() {
               public void keyPressed(KeyEvent e) {
                  if(e.getKeyCode() == KeyEvent.VK_ENTER){
                     user = userName.getText();
                     pass = new String(password.getPassword());
                     login(user,pass);
                  }
               }
            
               public void keyReleased(KeyEvent e) {  }
            
               public void keyTyped(KeyEvent e) { }
            }); 
      loginButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  user = userName.getText();
                  pass = new String(password.getPassword());
                  login(user,pass);
               }          
            });
    
    //listner for creating a new account
      newAcc.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  createNewAccount();
               }          
            });
    //starting to setup the looks of our login screen
      userName.setText("User Name");
      password.setText("Password");
    
    //add each element to their corresponding panels
      namePanel.add(userName);
      passPanel.add(password);
    
      userPanel.add(namePanel);
      userPanel.add(passPanel);
      buttPanel.add(loginButt);
      buttPanel.add(newAcc);
      loginPanel.add(userPanel);
      loginPanel.add(Box.createRigidArea(new Dimension(0,20)));
      loginPanel.add(buttPanel);
    
    //change the layout of these panels
      userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
      buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.X_AXIS));
      loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
    
    //finally add every panel to mainPanel
      mainPanel.setLayout(new GridBagLayout());
      mainPanel.add(loginPanel, new GridBagConstraints());
    
    //set the content pane of the frame to mainPanel and adds the background gif
      frame.setContentPane(wrapInBackgroundImage(mainPanel,new ImageIcon(PetSimGUI.class.getResource("menu gif.gif"))));    
      frame.setLocation(dim.width/2-LENGTH/2, dim.height/2-WIDTH/2); //sets location to the center 
      frame.setSize(LENGTH, WIDTH); //sets the size of the window
      frame.setVisible(true); //display it on screen
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
  
  //a function to print the login screen again after the user has finished creating an account
   void recreateLoginScreen(){
    
    //same stuff as above, somewhat less coding
      userName.setText("User Name");
      password.setText("Password");
    
      loginPanel.removeAll();
      userPanel.removeAll();
      passPanel.removeAll();
      buttPanel.removeAll();
   
   
      passPanel.add(password);
      userPanel.add(namePanel);
      userPanel.add(passPanel);
      buttPanel.add(loginButt);
      buttPanel.add(newAcc);
      loginPanel.add(userPanel);
      loginPanel.add(Box.createRigidArea(new Dimension(0,20)));
      loginPanel.add(buttPanel);
    
      passPanel.setLayout(new FlowLayout());
      userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
    
    
      frame.setContentPane(wrapInBackgroundImage(mainPanel,new ImageIcon(PetSimGUI.class.getResource("menu gif.gif"))));    
      frame.setVisible(true);
   
   }
  
  //method for when the user clicks create new account
   void createNewAccount(){
    //clear everything in the panels that we need
      loginPanel.removeAll();
      userPanel.removeAll();
      passPanel.removeAll();
      buttPanel.removeAll();
    
    //set up the textboxes and as well as the buttons.
      newUserTextField.setText(""); 
      newPassField.setText(""); 
      newPassAgainField.setText(""); 
    
      newPassAgainField.setFocusable(true);
      newPassAgainField.addKeyListener(
            new KeyListener() {
               public void keyPressed(KeyEvent e) {
                  if(e.getKeyCode() == KeyEvent.VK_ENTER){
                     checkValidAcc();
                  }
               }
            
               public void keyReleased(KeyEvent e) {  }
            
               public void keyTyped(KeyEvent e) { }
            }); 
    //listners of the two buttons
      createAcc.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  checkValidAcc();
               }          
            });
      backButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  recreateLoginScreen();
               }          
            });
   
    //add the elements in the panels and set their layouts
      userPanel.add(newUserName);
      userPanel.add(newUserTextField);
      userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
    
      passPanel.add(newPassword);
      passPanel.add(newPassField);
      passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.X_AXIS));
    
      passAgainPanel.add(newPassAgain);
      passAgainPanel.add(newPassAgainField);
      passAgainPanel.setLayout(new BoxLayout(passAgainPanel, BoxLayout.X_AXIS));
    
      buttPanel.add(createAcc);
      buttPanel.add(backButt);
    
      loginPanel.add(userPanel);
      loginPanel.add(Box.createRigidArea(new Dimension(0,10)));
      loginPanel.add(passPanel);
      loginPanel.add(Box.createRigidArea(new Dimension(0,10)));
      loginPanel.add(passAgainPanel);
      loginPanel.add(Box.createRigidArea(new Dimension(0,50)));
      loginPanel.add(buttPanel);
    
      mainPanel.setLayout(new GridBagLayout());
   
    //update the frame with mainPanel and make it visible    
      frame.setContentPane(mainPanel);
      frame.setVisible(true);
   
   }
  
  // checks whether the user name and password matches the requirements
   void checkValidAcc(){
      String newU = newUserTextField.getText();
      String newP = new String(newPassField.getPassword());
      String newPAgain = new String(newPassAgainField.getPassword());
   
   
      if(newU.length() < 4) JOptionPane.showMessageDialog(frame, "Please enter an username of length 4 or above!","Error",JOptionPane.ERROR_MESSAGE);
      else if(!newP.equals(newPAgain)) JOptionPane.showMessageDialog(frame, "Passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
      else if(newP.length() < 4) JOptionPane.showMessageDialog(frame, "Please enter a password of length 4 or above!","Error",JOptionPane.ERROR_MESSAGE);
      else if(!uDatabase.newUser(newU, newP)) JOptionPane.showMessageDialog(frame, "Username already exists! Please enter a new one!","Error",JOptionPane.ERROR_MESSAGE);
      else{
         uDatabase.newUser(newU, newP);
         uDatabase.saveUser();
         JOptionPane.showMessageDialog(frame, "Congratulations! Your account has been made!","Success",JOptionPane.INFORMATION_MESSAGE);
         recreateLoginScreen();
      }
   }
  
  // login
   void login(String uName, String uPass){
      int loginStat = uDatabase.login(uName, uPass);
      if(loginStat == 1){ // login is successful
         System.out.println(uDatabase.curUser.getCurPet());
      // selects curPet, or assigns a pet to a new player
         if(uDatabase.curUser.pDatabase.loadPet(uDatabase.curUser.getUserID())){
            choosePet(uDatabase.curUser);
         }
         else{
            buyFirstPet(uDatabase.curUser);
         }
      }
      else if(loginStat == 0){ // incorrect password
         JOptionPane.showMessageDialog(frame, "Incorrect password!","Error",JOptionPane.ERROR_MESSAGE);
      }
      else{ // user does not exist
         JOptionPane.showMessageDialog(frame, "User does not exist!","Error",JOptionPane.ERROR_MESSAGE);
      }
   }
  
  // gives user a pet without charging gold
   void buyFirstPet(final User player){
      String name = "Pet Name";
      String[] options = new String[] {"Cat 1", "Cat 2", "Cat 3", "Cancel"};
      JLabel catOneLook = new JLabel("Cat 1", cat1, JLabel.CENTER);
      JLabel catTwoLook = new JLabel("Cat 2", cat2, JLabel.CENTER);
      JLabel catThreeLook = new JLabel("Cat 3", cat3, JLabel.CENTER);
      final JLabel message[] = {catOneLook, catTwoLook, catThreeLook};
         
      JOptionPane.showMessageDialog(frame, "Choose your first pet to start playing!", "Tips", JOptionPane.INFORMATION_MESSAGE);
        
      int choice = JOptionPane.showOptionDialog(frame,message,"Choose pet",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
        
      if(choice != 3 && choice != JOptionPane.CLOSED_OPTION){
         name = JOptionPane.showInputDialog(frame, "Give your pet a name!", "Give Pet Name", JOptionPane.PLAIN_MESSAGE);
      }
        
        
      if(choice == 0){
         player.pDatabase.buyCat1(name);
         JOptionPane.showMessageDialog(frame, "Congrats! You've selected your first pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
      }
      else if(choice == 1){
         player.pDatabase.buyCat2(name);
         JOptionPane.showMessageDialog(frame, "Congrats! You've selected your first pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
      }
      else if(choice == 2){
         player.pDatabase.buyCat3(name);
         JOptionPane.showMessageDialog(frame, "Congrats! You've selected your first pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
      }
   
        // saves data
      player.pDatabase.savePet(player.getUserID());
      uDatabase.saveUser();
   
   }
  
  // choose a Cat out of three
   void choosePet(final User player){
      JLabel catOneLabel = new JLabel(cat1);
      JLabel catTwoLabel = new JLabel(cat2, JLabel.CENTER);
      JLabel catThreeLabel = new JLabel(cat3, JLabel.CENTER);
   
      final Pet temps[] = player.pDatabase.getPetList();
   
   // clears screen and starts game
      mainPanel.removeAll();
   
      choiceOneButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  player.setCurPet(temps[0]);
                  startGame(uDatabase.curUser, uDatabase.curUser.getCurPet());
               }          
            });
      choiceTwoButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  player.setCurPet(temps[1]);
                  startGame(uDatabase.curUser, uDatabase.curUser.getCurPet());
               }          
            });
   
      choiceThreeButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  player.setCurPet(temps[2]);
                  startGame(uDatabase.curUser, uDatabase.curUser.getCurPet());
               }          
            });
   
      for(i = 0;i<player.pDatabase.getNumPet();i++){
      
         if(temps[i] instanceof Cat1){
            selectPanel[i] = new JPanel();
         
            selectPanel[i].setLayout(new BoxLayout(selectPanel[i], BoxLayout.Y_AXIS));
         
            selectPanel[i].add(catOneLabel);
            selectPanel[i].add(Box.createRigidArea(new Dimension(0,50)));
            if(i == 0) selectPanel[i].add(choiceOneButt);
            else if(i == 1) selectPanel[i].add(choiceTwoButt);
            else if(i == 2) selectPanel[i].add(choiceThreeButt);
         }
         else if(temps[i] instanceof Cat2){
            selectPanel[i] = new JPanel();
         
            selectPanel[i].setLayout(new BoxLayout(selectPanel[i], BoxLayout.Y_AXIS));
         
            selectPanel[i].add(catTwoLabel);
            selectPanel[i].add(Box.createRigidArea(new Dimension(0,50)));
            if(i == 0) selectPanel[i].add(choiceOneButt);
            else if(i == 1) selectPanel[i].add(choiceTwoButt);
            else if(i == 2) selectPanel[i].add(choiceThreeButt);
         
         }
         else{
            selectPanel[i] = new JPanel();
         
            selectPanel[i].setLayout(new BoxLayout(selectPanel[i], BoxLayout.Y_AXIS));
         
            selectPanel[i].add(catThreeLabel);
            selectPanel[i].add(Box.createRigidArea(new Dimension(0,50)));
            if(i == 0) selectPanel[i].add(choiceOneButt);
            else if(i == 1) selectPanel[i].add(choiceTwoButt);
            else if(i == 2) selectPanel[i].add(choiceThreeButt);
         
         }
         mainPanel.add(selectPanel[i]);
      }
   
      frame.setContentPane(mainPanel);
   }
  
  // game screen
   void startGame(final User player, final Pet pet){
      JLabel catOneLook = new JLabel("Cat 1", cat1, JLabel.CENTER);
      JLabel catTwoLook = new JLabel("Cat 2", cat2, JLabel.CENTER);
      JLabel catThreeLook = new JLabel("Cat 3", cat3, JLabel.CENTER);
    
      final JLabel message[] = {catOneLook, catTwoLook, catThreeLook};
      
      petNameLabel = new JLabel(pet.getName());
   
      mainPanel.removeAll();
      buttPanel.removeAll();
    
    
    // sets stat bars according to values
      hungerBar.setValue(pet.getHunger());
      hygieneBar.setValue(pet.getHygiene());
      energyBar.setValue(pet.getEnergy());
    
      goldLabel = new JLabel(Integer.toString(player.getGold()));
    
    // stats decrease as time passing
      ActionListener hungerListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               pet.setHunger(pet.getHunger()-1);
               hungerBar.setValue(pet.getHunger());
               player.pDatabase.savePet(player.getUserID());
            }
         };
      ActionListener hygieneListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               pet.setHygiene(pet.getHygiene()-1);
               hygieneBar.setValue(pet.getHygiene());
               player.pDatabase.savePet(player.getUserID());
            }
         };
      ActionListener energyListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               pet.setEnergy(pet.getEnergy()-1);
               energyBar.setValue(pet.getEnergy());
               player.pDatabase.savePet(player.getUserID());
            }
         };
    
    // gold and level updates
      ActionListener goldListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               goldLabel = new JLabel(Integer.toString(player.getGold()));
               goldPanel.removeAll();
               goldPanel.add(goldHeaderLabel);
               goldPanel.add(goldLabel);
               goldPanel.add(Box.createRigidArea(new Dimension(150,20)));
            
               frame.setContentPane(mainPanel);
            }
         };
    
      ActionListener levelListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               levelLabel = new JLabel(Integer.toString(player.getLevel()));
               levelPanel.removeAll();
               levelPanel.add(levelHeaderLabel);
               levelPanel.add(levelLabel);
               levelPanel.add(Box.createRigidArea(new Dimension(120,30)));
            
               frame.setContentPane(mainPanel);
            }
         };
    
    // timers 
      Timer levelTimer = new Timer(HIGHEST_DECAY_RATE, levelListener);
      Timer goldTimer = new Timer(HIGHEST_DECAY_RATE, goldListener);
      Timer hungerTimer = new Timer(HUNGER_DECAY_RATE, hungerListener);
      Timer hygieneTimer = new Timer(HYGIENE_DECAY_RATE, hygieneListener);
      Timer energyTimer = new Timer(ENERGY_DECAY_RATE, energyListener);
      hungerTimer.start();
      hygieneTimer.start();
      energyTimer.start();
      goldTimer.start();
      levelTimer.start();
    
    // stats display
      hungerPanel.setLayout(new BorderLayout());
      hygienePanel.setLayout(new BorderLayout());
      energyPanel.setLayout(new BorderLayout());
    
      hungerPanel.add(hungerBar, BorderLayout.NORTH);
      hungerPanel.add(hungerLabel,BorderLayout.CENTER);
    
      hygienePanel.add(hygieneBar, BorderLayout.NORTH);
      hygienePanel.add(hygieneLabel, BorderLayout.CENTER);
    
      energyPanel.add(energyBar, BorderLayout.NORTH);
      energyPanel.add(energyLabel, BorderLayout.CENTER);
    
      statusPanel.add(hungerPanel);
      statusPanel.add(Box.createRigidArea(new Dimension(30,0)));
      statusPanel.add(hygienePanel);
      statusPanel.add(Box.createRigidArea(new Dimension(30,0)));
      statusPanel.add(energyPanel);
    
    // feed, wash, and sleep buttons and action performs (gain exp, lose gold, gain stats)
      feedButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
               
               
                  if(player.setGold(player.getGold()-5)){
                  
                     pet.setHunger(pet.getHunger()+40);
                     hungerBar.setValue(pet.getHunger());
                     player.setExp(player.getExp() + 10);
                  
                     refreshTimer.restart();
                     doAction(pet.getEatGIF());
                     uDatabase.saveUser();
                     player.pDatabase.savePet(player.getUserID());
                  
                  }
                  else JOptionPane.showMessageDialog(frame, "Insufficient gold!","Error",JOptionPane.INFORMATION_MESSAGE);
               }          
            });
    
      washButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
               
                  if(player.setGold(player.getGold()-20)){
                  
                     pet.setHygiene(pet.getHygiene()+50);
                     hygieneBar.setValue(pet.getHygiene());
                     player.setExp(player.getExp() + 10);
                  
                     refreshTimer.restart();
                     doAction(pet.getBathGIF());
                     uDatabase.saveUser();
                     player.pDatabase.savePet(player.getUserID());
                  
                  }
                  else JOptionPane.showMessageDialog(frame, "Insufficient gold!","Error",JOptionPane.INFORMATION_MESSAGE);
               }          
            });
   
      sleepButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
               
                  pet.setEnergy(100);
                  energyBar.setValue(pet.getEnergy());
                  pet.setHunger(pet.getHunger()-20);
                  hungerBar.setValue(pet.getHygiene());
                  player.setExp(player.getExp() + 50);
               
                  refreshTimer.restart();
                  doAction(pet.getSleepGIF());
                  uDatabase.saveUser();
                  player.pDatabase.savePet(player.getUserID());
               
               }          
            });
    
    // plays a minigame on console
      miniGamesButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
                  JOptionPane.showMessageDialog(frame, "Please check the console!","Tips",JOptionPane.INFORMATION_MESSAGE);
               
                  boolean result = Game.playGame();
                  if(result){
                     player.setGold(player.getGold()+10);
                     player.setExp(player.getExp()+10);
                  }
                  uDatabase.saveUser();
               }          
            });
    
    // allows user to buy a new pet
      buyPetButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
                  String name = "Pet Name";
                  String[] options = new String[] {"Cat 1", "Cat 2", "Cat 3", "Cancel"};
               
                  JOptionPane.showMessageDialog(frame, "Watch out! Each pet costs 500 gold. Spend your gold wisely!", "Pet pricing", JOptionPane.INFORMATION_MESSAGE);
               
                  int choice = JOptionPane.showOptionDialog(frame,message,"Buy pet",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
               
                  if(choice != 3 && choice != JOptionPane.CLOSED_OPTION){
                     name = JOptionPane.showInputDialog(frame, "Give your pet a name!", "Give Pet Name", JOptionPane.PLAIN_MESSAGE);
                  }
               
                  if(player.getGold() >= 500){
                  
                     if(choice == 0 && player.pDatabase.okToBuy(0)){
                        player.setGold(player.getGold()-500);
                        player.pDatabase.buyCat1(name);
                        JOptionPane.showMessageDialog(frame, "Congrats! You've purchased another pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else if(choice == 1 && player.pDatabase.okToBuy(1)){
                        player.setGold(player.getGold()-500);
                        player.pDatabase.buyCat2(name);
                        JOptionPane.showMessageDialog(frame, "Congrats! You've purchased another pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else if(choice == 2 && player.pDatabase.okToBuy(2)){
                        player.setGold(player.getGold()-500);
                        player.pDatabase.buyCat3(name);
                        JOptionPane.showMessageDialog(frame, "Congrats! You've purchased another pet! Log in again to play with them!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                     }
                     else if(choice != 3 && choice != JOptionPane.CLOSED_OPTION) JOptionPane.showMessageDialog(frame, "You cannot own two of the same type of pets!", "Warning!", JOptionPane.WARNING_MESSAGE);
                  
                  } 
                  else if(choice != 3 && choice != JOptionPane.CLOSED_OPTION)JOptionPane.showMessageDialog(frame, "You need 500 gold to purchase another pet!!", "Error!", JOptionPane.ERROR_MESSAGE);
               
                  player.pDatabase.savePet(player.getUserID());
                  uDatabase.saveUser();
               }          
            });
    
      rankingButt.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  setAllButtonsFalse();
                  JOptionPane.showMessageDialog(frame, "Please check the console!","Tips",JOptionPane.INFORMATION_MESSAGE);
                  uDatabase.printALlInfo();     
               }          
            });
    
    // add buttons to panel
      buttPanel.add(feedButt);
      buttPanel.add(Box.createRigidArea(new Dimension(15,0)));
      buttPanel.add(washButt);
      buttPanel.add(Box.createRigidArea(new Dimension(15,0)));
      buttPanel.add(sleepButt);
    
      buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.X_AXIS));
    
      goldPanel.setLayout(new BoxLayout(goldPanel, BoxLayout.X_AXIS));
      goldPanel.add(goldHeaderLabel);
      goldPanel.add(goldLabel);
      goldPanel.add(Box.createRigidArea(new Dimension(150,20)));
    
      levelPanel.setLayout(new BoxLayout(levelPanel, BoxLayout.X_AXIS));
      levelPanel.add(levelHeaderLabel);
      levelPanel.add(levelLabel);
      levelPanel.add(Box.createRigidArea(new Dimension(120,30)));
    
      controlPanel.add(goldPanel);
      controlPanel.add(levelPanel);
      controlPanel.add(statusPanel);
      controlPanel.add(buttPanel);
      controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
      controlPanel.add(miniGamesButt);
      controlPanel.add(buyPetButt);
      controlPanel.add(rankingButt);
      
      controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
      petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.X_AXIS));
    
      petPanel.add(Box.createRigidArea(new Dimension(100,0)));  
      petPanel.add(new JLabel(new ImageIcon(PetSimGUI.class.getResource(pet.getNormGIF()))));
      petPanel.add(petNameLabel);
    
      petPanel.setPreferredSize(new Dimension(400,400));
    
      bigPPanel.setLayout(new BoxLayout(bigPPanel, BoxLayout.Y_AXIS));
   
      bigPPanel.add(petPanel);
      bigPPanel.add(petNameLabel);
    
      bigPPanel.setPreferredSize(new Dimension(400,180));
   
    
      ActionListener petListener = 
         new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
               refresh(player, pet);
               setAllButtonsTrue();
            }
         };
      refreshTimer = new Timer(REFRESH_RATE, petListener);
      refreshTimer.start();
    
    
    
    
    
      mainPanel.add(controlPanel);
      mainPanel.add(bigPPanel);
    
      frame.pack();
        
      frame.setSize(LENGTH,WIDTH);
      frame.setContentPane(mainPanel);
   
   }
  
  // pets do action
   void doAction(String action){
      petPanel.removeAll();
      petPanel.add(Box.createRigidArea(new Dimension(100,0))); 
      petPanel.add(new JLabel(new ImageIcon(PetSimGUI.class.getResource(action))));
      frame.setContentPane(mainPanel);
   
   }
  
   void refresh(final User player, final Pet pet){
      petPanel.removeAll();
      petPanel.add(Box.createRigidArea(new Dimension(100,0))); 
      if(pet.getHunger() < 100){ 
      
         petPanel.add(new JLabel(new ImageIcon(PetSimGUI.class.getResource(pet.getNormGIF()))));
         frame.setContentPane(mainPanel);
      }
   }
  
   void setAllButtonsFalse(){
      feedButt.setEnabled(false);
      washButt.setEnabled(false);
      sleepButt.setEnabled(false);
      miniGamesButt.setEnabled(false);
   }
  
   void setAllButtonsTrue(){
      feedButt.setEnabled(true);
      washButt.setEnabled(true);
      sleepButt.setEnabled(true);
      miniGamesButt.setEnabled(true);
   }
  

   public static void main(String[] args){
      uDatabase = new UserDatabase();
      PetSimGUI logScreen = new PetSimGUI(); // starts program by logging in 
   }
  
}