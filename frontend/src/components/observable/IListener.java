package components.observable;

public interface IListener<ChangeInfo> {

    void onChange(ChangeInfo changeInfo);

}
