package br.com.bairroemdia.service;
import br.com.bairroemdia.dto.ApiDtos.PrevisaoResponse; import br.com.bairroemdia.domain.Ocorrencia;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.web.client.RestClient; import org.springframework.web.client.RestClientException;
import java.util.Map;
@Service
public class MlClient {
 private final RestClient client;
 public MlClient(@Value("${ml.service-url}") String url){client=RestClient.builder().baseUrl(url).build();}
 public PrevisaoResponse prever(Ocorrencia o){
  try { Map<?,?> r=client.post().uri("/predict").body(Map.of("categoria",o.getCategoria().getNome(),"regiao",o.getBairro().getNome(),"mes",o.getDataCriacao().getMonthValue(),"dia_semana",o.getDataCriacao().getDayOfWeek().getValue(),"canal","MOBILE")).retrieve().body(Map.class);
   if(r==null)return new PrevisaoResponse("INDISPONIVEL","Serviço ML sem resposta",null);
   Object p=r.get("probabilidade_atraso");return new PrevisaoResponse("DISPONIVEL","Estimativa estatística; não é garantia de prazo",p instanceof Number n?n.doubleValue():null);
  } catch(RestClientException e){return new PrevisaoResponse("INDISPONIVEL","Previsão temporariamente indisponível",null);}
 }
}
