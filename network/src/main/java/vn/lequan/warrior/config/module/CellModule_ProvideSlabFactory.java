package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Slab;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvideSlabFactory implements Factory<Slab> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvideSlabFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Slab get() {
    return Preconditions.checkNotNull(
        module.provideSlab(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Slab> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvideSlabFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#provideSlab(App)}. */
  public static Slab proxyProvideSlab(CellModule instance, App app) {
    return instance.provideSlab(app);
  }
}
