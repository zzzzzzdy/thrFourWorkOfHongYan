package JsonFormatToolOne;

import java.io.IOException;
import java.util.*;

public class GetJsonMessage {
    static Map<String, List<String>> [] maps = new Map[100000];
    static int isTrue = 0;
    static int isTrue2 = 0;
    static int a = 0;



    public void getMessage(String json){
        int length = json.length();
        int x = json.indexOf("data");

        char k1,k2=0,k3;

        String value = null;
        String value2 = null;
        String key = null;
        for(int i = x,j=i+2; j < length; i++,j++)
        {
            k1 = json.charAt(i);
            if(k1=='{')
            {
                maps[a] = new HashMap<String,List<String>>();

                int i1 = i;
                while(json.charAt(i1)!='}')
                {
                    List<String> list = new ArrayList();
                    i1++;
                    if(json.charAt(i1)==':')
                    {
                        int i2 = i1 - 2;
                        int i3 = i1 + 2;
                        while(json.charAt(i2)!='"')
                        {
                            i2--;


                        }
                        if(json.charAt(i1+2)=='[')
                        {
                            while(json.charAt(i3)!=',')
                            {
                                i3++;
                            }
                            value = json.substring(i1+3,i3);
                            list.add(value);
                            int i4 = i3 + 1;
                            while(json.charAt(i4)!='"')
                            {
                                i4++;
                            }
                            while(json.charAt(i3)!=']')
                            {
                                i3++;
                            }
                            value2 = json.substring(i4,i3);
                            list.add(value2);

                        }
                        else{
                            while(json.charAt(i3)!=',')
                            {
                                i3++;
                            }
                            value = json.substring(i1+2,i3);
                            list.add(value);

                    }


                        key = json.substring(i2+1,i1-1);
                        maps[a].put(key,list);



                        System.out.println(key+":"+list);

                    }

                }
                System.out.println("---------------");
                a++;
            }
        }

    }
    public static void main(String[] args) {


        JsonFormatTool json = new JsonFormatTool();
        String str = null;
        try {
            str = json.readFileContent("D:\\ideaProjects\\ZDY-WORK\\src\\JsonFormatToolOne\\Json.file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = json.formatJson(str);

        System.out.println("Json格式:\n"+result);
        System.out.println("===================" +
                "=======================\n");
        System.out.println("提取的有效内容：\n");
        GetJsonMessage one =new GetJsonMessage();
        one.getMessage(result);
        System.out.println("请输入你想要知道谁的信息(里脊/郑胖胖/朱大)：");
        Scanner sc = new Scanner(System.in);
        String Iname = sc.nextLine();
        for(int j = 0;j<a;j++)
        {
            for (Map.Entry<String, List<String>> in: maps[j].entrySet()) {
                if(in.getValue().contains("\""+Iname+"\""))
                {
                    isTrue = 1;

                    break;



                }

            }
            if(isTrue==1)
            {
                for (Map.Entry<String, List<String>> in: maps[j].entrySet())
                {
                    System.out.println("key===  "+in.getKey()+"     values===  "+in.getValue());
                }
                isTrue = 0;

            }
        }
        System.out.println("输入nb内容可查询此人是谁，请输入(有女朋友/高中学Android/Offer收割机...)：");
        String nb = sc.nextLine();
        for(int j = 0;j<a;j++)
        {
            for (Map.Entry<String, List<String>> in: maps[j].entrySet()){
                if(in.getValue().contains("\""+nb+"\""))
                {
                    isTrue2 = 1;

                    System.out.println(maps[j].get("name")+" "+nb);
                }

            }

        }


    }



}
