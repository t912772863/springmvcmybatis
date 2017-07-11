package springstudy.knights;

/**
 * 勇士类
 * Created by Administrator on 2017/6/20 0020.
 */
public class BraveKnight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest(){
        quest.embark();
    }
}
