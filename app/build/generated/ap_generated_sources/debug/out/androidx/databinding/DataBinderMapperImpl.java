package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new br.org.curitiba.ici.avaliacao.DataBinderMapperImpl());
  }
}
