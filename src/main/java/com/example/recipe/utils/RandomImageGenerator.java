package com.example.recipe.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomImageGenerator {

    public static List<String> randomFoodImageHolder = new ArrayList<>(
            Arrays.asList(
                    "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxfHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1504674900247-0877df9cc836?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyfHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzfHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw0fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw1fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw2fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1565958011703-44f9829ba187?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw3fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1606787366850-de6330128bfc?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw4fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw5fHxmb29kfGVufDB8fHx8MTY2NjUwNjg5NA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDY4OTQ&ixlib=rb-4.0.3&q=80&w=400",


                    "https://images.unsplash.com/photo-1467003909585-2f8a72700288?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1490818387583-1baba5e638af?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1484723091739-30a097e8f929?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxM3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1493770348161-369560ae357d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxNHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1499028344343-cd173ffc68a9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxNXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1498837167922-ddd27525d352?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxNnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1504754524776-8f4f37790ca0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxN3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1473093295043-cdd812d0e601?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxOHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxOXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1497034825429-c343d7c6a68f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyMHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg2MTI&ixlib=rb-4.0.3&q=80&w=400",


                    "https://images.unsplash.com/photo-1478145046317-39f10e56b5e9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyMXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1529042410759-befb1204b468?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyMnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1484980972926-edee96e0960d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyM3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyNHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1481931098730-318b6f776db0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyNXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1414235077428-338989a2e8c0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyNnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyN3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1505935428862-770b6f24f629?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyOHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1481070555726-e2fe8357725c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyOXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1501959915551-4e8d30928317?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzMHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MTU&ixlib=rb-4.0.3&q=80&w=400",


                    "https://images.unsplash.com/photo-1609951651556-5334e2706168?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzMXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1432139509613-5c4255815697?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzMnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1565299507177-b0ac66763828?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzM3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzNHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1490645935967-10de6ba17061?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzNXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1574484284002-952d92456975?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzNnx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1532980400857-e8d9d275d858?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzN3x8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1496116218417-1a781b1c416c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzOHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1541795795328-f073b763494e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzOXx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1586511934875-5c5411eebf79?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw0MHx8Zm9vZHxlbnwwfHx8fDE2NjY1MDg3MzI&ixlib=rb-4.0.3&q=80&w=400"

            ));


    public static List<String> randomPersonHolder = new ArrayList<>(
            Arrays.asList(
                    "https://images.unsplash.com/photo-1664574654561-4c54605b1372?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MXwxfHNlYXJjaHwxfHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyfHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwzfHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1544005313-94ddf0286df2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw0fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw1fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1552058544-f2b08422138a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw2fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1554151228-14d9def656e4?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw3fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1664575602554-2087b04935a5?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MXwxfHNlYXJjaHw4fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHw5fHxwZXJzb258ZW58MHx8fHwxNjY2NTA4Nzcw&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1491349174775-aaafddd81942?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMHx8cGVyc29ufGVufDB8fHx8MTY2NjUwODc3MA&ixlib=rb-4.0.3&q=80&w=400",


                    "https://images.unsplash.com/photo-1499952127939-9bbf5af6c51c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMXx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1492681290082-e932832941e6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxMnx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1494959764136-6be9eb3c261e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxM3x8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1500048993953-d23a436266cf?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxNHx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1664574652984-5b5f769bef07?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MXwxfHNlYXJjaHwxNXx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1504593811423-6dd665756598?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxNnx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1599566150163-29194dcaad36?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxN3x8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1473830394358-91588751b241?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxOHx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1496302662116-35cc4f36df92?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwxOXx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400",
                    "https://images.unsplash.com/photo-1580489944761-15a19d654956?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzQ1NTB8MHwxfHNlYXJjaHwyMHx8cGVyc29ufGVufDB8fHx8MTY2NjUwODgwNA&ixlib=rb-4.0.3&q=80&w=400"
            ));


    public static int randomImageGeneratorFood() {
        return ThreadLocalRandom.current().nextInt(randomFoodImageHolder.size()) % randomFoodImageHolder.size();
    }

    public static int randomImageGeneratorPerson() {
        return ThreadLocalRandom.current().nextInt(randomPersonHolder.size()) % randomPersonHolder.size();
    }

}
