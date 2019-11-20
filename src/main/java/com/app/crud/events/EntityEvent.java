package com.app.crud.events;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EntityEvent <T> implements ResolvableTypeProvider {
    private T source ;

    public EntityEvent(T source ) {
        this.source = source ;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),ResolvableType.forInstance(source));
    }
}
