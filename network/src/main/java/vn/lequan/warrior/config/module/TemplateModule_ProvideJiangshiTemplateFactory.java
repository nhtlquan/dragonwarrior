package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.monsters.JiangshiTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TemplateModule_ProvideJiangshiTemplateFactory
    implements Factory<JiangshiTemplate> {
  private final TemplateModule module;

  private final Provider<App> appProvider;

  public TemplateModule_ProvideJiangshiTemplateFactory(
      TemplateModule module, Provider<App> appProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
  }

  @Override
  public JiangshiTemplate get() {
    return Preconditions.checkNotNull(
        module.provideJiangshiTemplate(appProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<JiangshiTemplate> create(TemplateModule module, Provider<App> appProvider) {
    return new TemplateModule_ProvideJiangshiTemplateFactory(module, appProvider);
  }

  /** Proxies {@link TemplateModule#provideJiangshiTemplate(App)}. */
  public static JiangshiTemplate proxyProvideJiangshiTemplate(TemplateModule instance, App app) {
    return instance.provideJiangshiTemplate(app);
  }
}
