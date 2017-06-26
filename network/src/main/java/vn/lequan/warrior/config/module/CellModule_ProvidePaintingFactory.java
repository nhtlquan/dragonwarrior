package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Painting;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvidePaintingFactory implements Factory<Painting> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvidePaintingFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Painting get() {
    return Preconditions.checkNotNull(
        module.providePainting(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Painting> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvidePaintingFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#providePainting(App)}. */
  public static Painting proxyProvidePainting(CellModule instance, App app) {
    return instance.providePainting(app);
  }
}
