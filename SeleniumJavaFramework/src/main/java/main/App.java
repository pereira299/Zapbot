package main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class App {
	static WebDriver driver;
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/FUNCIONARIO2/Desktop/chromedriver.exe");
		driver = new ChromeDriver();
		
	    driver.get("https://web.whatsapp.com/");
	    Thread.sleep(5000);  // Let the user actually see something!
	    boolean existe;
	    do{
	    	
	    	try{
	    		
	    		WebElement qrcode = driver.findElement(By.cssSelector("img[alt='Scan me!']"));
	    		existe = true;
	    		System.out.println("Pagina requisita a verificaçao por QRcode");
	    		Thread.sleep(2000);
	    	}catch (Exception e) {
	    		Thread.sleep(2000);
	    		try{
	    			
	    			WebElement again = driver.findElement(By.cssSelector("div[class='_13HPh']"));
	    			existe = true;
	    			System.out.println("Falha de conexao. Tentando Novamente...");
	    		}catch (Exception ex) {
	    			existe = false;
				}
	    	}
	    }while(existe);
	    
	    System.out.println("Entrou!!");
	    Thread.sleep(2000);
	    
	    App app = new App();
	    app.sendToChat("Transferência");
	    
	    
	}
	
	public boolean sendToChat(String chatName) throws InterruptedException{
		WebElement newChat = null;
		boolean existe;
	    //verificando se a pagina esta carregada
	    do{
	    	try{
	    		newChat = driver.findElement(By.cssSelector("div[title='Nova conversa']"));
	    		existe = true;
	    	}catch (Exception e) {
				existe = false;
			}
	    }while(!existe);
	    
	    //procurando conversa
		Thread.sleep(3000);
	   
	    newChat.click();
	    
	    WebElement searchBox= driver.findElement(By.cssSelector("input[title='Buscar contatos']"));
	    searchBox.click();
	    searchBox.sendKeys(chatName);
	    Thread.sleep(2000);
	    String find = "span[title = "+ chatName +"]";
	    try{
	    	WebElement chat = driver.findElement(By.cssSelector(find));
	    	chat.click();
	    }catch(Exception e){
	    	System.out.println("A conversa "+chatName.toUpperCase()+" nao foi encontrada");
	    	return false;
	    }
	    return true;
	}
}
