package vn.lequan.warrior.config.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import vn.lequan.warrior.entity.monsters.Jiangshi;
import vn.lequan.warrior.entity.monsters.JiangshiTemplate;
import vn.lequan.warrior.util.App;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CharacterModule_ProvideJiangshiFactory implements Factory<Jiangshi> {
  private final CharacterModule module;

  private final Provider<App> appProvider;

  private final Provider<JiangshiTemplate> templateProvider;

  public CharacterModule_ProvideJiangshiFactory(
      CharacterModule module,
      Provider<App> appProvider,
      Provider<JiangshiTemplate> templateProvider) {
    assert module != null;
    this.module = module;
    assert appProvider != null;
    this.appProvider = appProvider;
    assert templateProvider != null;
    this.templateProvider = templateProvider;
  }

  @Override
  public Jiangshi get() {
    return Preconditions.checkNotNull(
        module.provideJiangshi(appProvider.get(), templateProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Jiangshi> create(
      CharacterModule module,
      Provider<App> appProvider,
      Provider<JiangshiTemplate> templateProvider) {
    return new CharacterModule_ProvideJiangshiFactory(module, appProvider, templateProvider);
  }

  /** Proxies {@link CharacterModule#provideJiangshi(App, JiangshiTemplate)}. */
  public static Jiangshi proxyProvideJiangshi(
      CharacterModule instance, App app, JiangshiTemplate template) {
    return instance.provideJiangshi(app, template);
  }
}
