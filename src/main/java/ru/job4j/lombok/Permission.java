package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import java.util.List;

@Builder(builderMethodName = "of")
@Getter
@ToString
public class Permission {

    private int id;

    private String name;

    @Singular("rule")
    private List<String> rules;
}
