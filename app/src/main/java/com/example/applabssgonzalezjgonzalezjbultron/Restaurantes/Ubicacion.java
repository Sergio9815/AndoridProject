package com.example.applabssgonzalezjgonzalezjbultron.Restaurantes;

import android.content.Intent;
import android.net.Uri;

public class Ubicacion {

    private String ub;

    public String getUb() {
        return ub;
    }

    public void setUb(String nom) {
        if((nom.compareTo("KFC") == 0)) {
            nom = "https://www.google.com/maps/dir/8.9457366,-79.6838868/kfc+vista+panama/@8.9907403,-79.6773267,12z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x8faca824935af199:0x533654fa724aa21c!2m2!1d-79.5308882!2d9.0303051";
        }
        else if((nom.compareTo("Subway") == 0)) {
            nom = "https://www.google.com/maps/dir//subway/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca867f0716655:0x26432b17a4d3a5a1?sa=X&ved=2ahUKEwjV29-74armAhWJjVkKHRSpBHwQ9RcwAHoECAEQCQ";
        }
        else if((nom.compareTo("Burger King") == 0)) {
            nom = "https://www.google.com/maps/dir//Burger+King+%7C+El+Dorado,+Panam%C3%A1/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca85ca2d7a777:0xd9a99a62e771e9d9?sa=X&ved=0ahUKEwiNnZyj4qrmAhWG2FkKHcjxD38Q48ADCCswAA";
        }
        else if((nom.compareTo("McDonalds") == 0)) {
            nom = "https://www.google.com/maps/dir//macdonald+panama/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca8f7e3807d93:0x4b70f2a4fc47e93?sa=X&ved=2ahUKEwiOotq54qrmAhXBmVkKHbuPCzEQ9RcwAHoECAEQCQ";
        }
        else if((nom.compareTo("Dominos Pizza") == 0)) {
            nom = "https://www.google.com/maps/dir//dominos+pizza/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca8233ce00101:0xb519b56340ed6398?sa=X&ved=2ahUKEwiVxt_p4qrmAhWJuVkKHSZxCEQQ9RcwAHoECAEQCQ";
        }
        else if((nom.compareTo("Starbucks") == 0)) {
            nom = "https://www.google.com/maps/dir//starbucks/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca82320d72453:0x549cfea0aba30501?sa=X&ved=2ahUKEwjpqrb94qrmAhXhw1kKHV6qBdkQ9RcwAHoECAEQCQ";
        }
        else if((nom.compareTo("Pizza Hut") == 0)) {
            nom = "https://www.google.com/maps/dir//pizza+hut/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca84248848ccf:0x6496ffeadb659e89?sa=X&ved=2ahUKEwi7lr6a46rmAhXkx1kKHbQJAmsQ9RcwAHoECAEQCQ";
        }
        else if((nom.compareTo("Dunkin Donuts") == 0)) {
            nom = "https://www.google.com/maps/dir//dunkin+donuts/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x8faca8986ecb0b15:0x1e3802c59e2bec4e?sa=X&ved=2ahUKEwjtodTA46rmAhWBjVkKHV3oCdsQ9RcwAHoECAEQCA";
        }
	    else if((nom.compareTo("Helados Keenes") == 0)) {
            nom = "https://www.google.com/maps/dir/8.9681514,-79.706331/Helados+Keene's/@8.9907205,-79.7499413,11z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x8faca900efd95661:0xe82d5afe38030341!2m2!1d-79.511842!2d8.9934";
        }


        this.ub = nom;
    }


}
