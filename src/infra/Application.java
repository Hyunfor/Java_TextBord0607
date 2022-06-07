package infra;

import controller.Controller;

import java.awt.*;
import java.util.Scanner;

public class Application {

    private Scanner sc = Container.sc;
    private boolean isActive = true;

    private String applicationName;

    public Application(String applicationName){
        this.applicationName = applicationName;
    }

    public void run(){

        while(isActive){

            String domain = "https://" + applicationName;

            System.out.print(domain);
            String inputUri = sc.nextLine().trim();

            Request request = new Request(inputUri);

            Filter filter = new Filter(request);

            if(!filter.isValidRequest()){
                System.out.println("잘못된 요청입니다.");
                continue;
            }

            Controller controller = getController(request.getControllerCode());

            if(controller != null){
                controller.execute(request);
            } else {
                System.out.println("올바른 URI를 입력해주세요.");
            }

        }

    }

    // /members
    public Controller getController(String code){

        switch (code){
            case "system":
                return Container.systemController;
            case "member" :
            case "members" :
                return Container.memberController;
            default:
                return null;
        }

    }


}
