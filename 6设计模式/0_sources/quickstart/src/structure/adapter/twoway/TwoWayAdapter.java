package structure.adapter.twoway;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class TwoWayAdapter implements Target,Source {

    private SourceAdaptee sourceAdaptee;
    private TargetAdaptee targetAdaptee;

    public TwoWayAdapter() {
        this.sourceAdaptee = new SourceAdaptee();
        this.targetAdaptee = new TargetAdaptee();
    }

    @Override
    public void soureRequest() {
        this.targetAdaptee.TargetRequest();
    }

    @Override
    public void targetRequest() {
        this.sourceAdaptee.SourceRequest();
    }
}
