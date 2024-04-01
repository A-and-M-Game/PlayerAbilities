package kerbal.playerabilities.component;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface FloatComponent extends Component {
    float getValue();
    void setValue(float newValue);
    void addValue(float offset);
}