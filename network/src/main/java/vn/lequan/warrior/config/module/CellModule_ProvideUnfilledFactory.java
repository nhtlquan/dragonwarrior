package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.map.cells.Unfilled;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CellModule_ProvideUnfilledFactory implements Factory<Unfilled> {
  private final CellModule module;

  private final Provider<App> appProvider;

  public CellModule_ProvideUnfilledFactory(CellModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public Unfilled get() {
    return Preconditions.checkNotNull(
        module.provideUnfilled(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Unfilled> create(CellModule module, Provider<App> appProvider) {
    return new CellModule_ProvideUnfilledFactory(module, appProvider);
  }

  /** Proxies {@link CellModule#provideUnfilled(App)}. */
  public static Unfilled proxyProvideUnfilled(CellModule instance, App app) {
    return instance.provideUnfilled(app);
  }
}
