package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideAppFactory implements Factory<App> {
  private final AppModule module;

  public AppModule_ProvideAppFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public App get() {
    return Preconditions.checkNotNull(
        module.provideApp(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<App> create(AppModule module) {
    return new AppModule_ProvideAppFactory(module);
  }

  /** Proxies {@link AppModule#provideApp()}. */
  public static App proxyProvideApp(AppModule instance) {
    return instance.provideApp();
  }
}
