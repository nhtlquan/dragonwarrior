package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.features.debug.DebugBox2D;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class FeatureModule_ProvideDebugBox2DFactory implements Factory<DebugBox2D> {
  private final FeatureModule module;

  private final Provider<App> appProvider;

  public FeatureModule_ProvideDebugBox2DFactory(FeatureModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public DebugBox2D get() {
    return Preconditions.checkNotNull(
        module.provideDebugBox2D(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<DebugBox2D> create(FeatureModule module, Provider<App> appProvider) {
    return new FeatureModule_ProvideDebugBox2DFactory(module, appProvider);
  }

  /** Proxies {@link FeatureModule#provideDebugBox2D(App)}. */
  public static DebugBox2D proxyProvideDebugBox2D(FeatureModule instance, App app) {
    return instance.provideDebugBox2D(app);
  }
}
