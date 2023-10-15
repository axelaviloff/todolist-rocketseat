package br.com.rocketseat.todolist.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@UtilityClass
public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        Arrays.asList(pds).forEach(pd -> {
            if (Objects.isNull(src.getPropertyValue(pd.getName()))) {
                emptyNames.add(pd.getName());
            }
        });

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
