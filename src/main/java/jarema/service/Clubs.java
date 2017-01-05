package jarema.service;

import java.util.List;

/**
 * Created by Jarema on 05.01.2017.
 */
public class Clubs {
    final int ammount;
    final List<String> list;

    public Clubs(List<String> list) {
        this.ammount = list.size();
        this.list = list;
    }

    public int getAmmount() {
        return ammount;
    }

    public List<String> getList() {
        return list;
    }

}
