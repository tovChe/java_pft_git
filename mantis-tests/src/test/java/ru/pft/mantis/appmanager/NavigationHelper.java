package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void editPage() throws InterruptedException {

    wd.findElement(By.xpath("//li[6]//a[1]//span[1]")).click();
    wd.findElement(By.xpath("//div[@class='main-content']//li[2]//a[1]")).click();
  }
}
