package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface MultiStringComponent extends Component {
    public String getValue(int i);
    public void setValue(int i, String str);
}