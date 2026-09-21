package com.vasnova.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class FakeIoTRepository_Factory implements Factory<FakeIoTRepository> {
  @Override
  public FakeIoTRepository get() {
    return newInstance();
  }

  public static FakeIoTRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeIoTRepository newInstance() {
    return new FakeIoTRepository();
  }

  private static final class InstanceHolder {
    private static final FakeIoTRepository_Factory INSTANCE = new FakeIoTRepository_Factory();
  }
}
