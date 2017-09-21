package onetwopunch.seoulinsangshot.com.seoulinsangshot.VO;

import java.util.ArrayList;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Weather1;

/**
 * Created by 301 on 2017-09-21.
 */

public class GsonVO_weather1 {
    Response response;

    public class Response {

        Header header;
        Body body;
        ///////////////
        public class Header {
            String resultCode;
            String resultMsg;

            public String getResultCode() {
                return resultCode;
            }

            public String getResultMsg() {
                return resultMsg;
            }
        }
        ///////////////////////
        public class Body {
            Items items;

            public class Items {
                ArrayList<Model_Weather1> item= new ArrayList<Model_Weather1>();

                //getItem()
                public ArrayList<Model_Weather1> getItem() {
                    return item;
                }

            }
            //getItems()
            public Items getItems() {
                return items;
            }
        }
        ///////헤더는 필요없다///////
        public Header getHeader() {
            return header;
        }
        //////////////////

        public Body getBody() {
            return body;
        }

    }

    public Response getResponse() {
        return response;
    }
}
