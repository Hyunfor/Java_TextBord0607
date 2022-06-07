package infra;

import config.ApplicationConfig;

public class Filter {

    public Request request;

    public Filter(Request request){
        this.request = request;
    }

    public boolean isValidRequest(){

        String originCode = request.getOriginUrl();

        String sortCode = sorting(originCode);

        boolean isLogon = request.isLogon();

        if(sortCode.equals("ANONYMOUS")){  // 권한 없는 url
            if(isLogon){ // 로그인 o
                return false;
            }
        } else if(sortCode.equals("HASAUTH")){ // 권한이 있는 url
            if(!isLogon){ // 로그인 x
                return false;
            }
        }

        return true;

    }

    private String sorting(String url){

        if(isAnonymous(url)){
            return "ANONYMOUS";
        }
        if(isNeedAuth(url)){
            return "HASAUTH";
        }
        return "PERMITALL";
    }

    private boolean isNeedAuth(String url){

        String[] hasAuthUrlList = ApplicationConfig.hasAuthUrlList;

        for(String regUrl : hasAuthUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymous(String url){

        String[] anonymousUrlList = ApplicationConfig.anonymousUrlList;

        for(String regUrl : anonymousUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }


}
