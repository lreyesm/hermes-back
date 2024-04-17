/*package es.eroski.phermesback.service;

import org.springframework.stereotype.Service;

import es.eroski.phermesback.client.LpuEncolarRequestType;
import es.eroski.phermesback.client.LpuEncolarRequestType.Parametros;
import es.eroski.phermesback.client.LpuEncolarResponse;

@Service
public class HermesClient extends WebServiceGatewaySupport {

	public LpuEncolarResponse getEncolar() {
		LpuEncolarRequestType request = new LpuEncolarRequestType();
		request.setUsuario("S2469");
		request.setPrograma("PRNODO");
		request.setProceso("PRUAPILPU");
		Parametros parametro = new Parametros();

		String cadena1 = "tmp_path=/tmp/;tmp_data_file=H_LOCALIZACIONES_DUMMY_TMP;IdProyecto=1;IdEscenario=2;IdEjecucion=3;IdTienda=9999;RotuloCompetencia=Tortulo_Dummy</lpu:Parametro";
		String cadena2 = "tmp_path_file=/tmp/H_LOCALIZACIONES_DUMMY_TMP;tmp_path_file_copia=/tmp/H_LOCALIZACIONES_DUMMY_COPIA_TMP";
		// parametro.setCadena(null);

		request.setParametros(null);
		request.setGrupo("EySS Planificacion");
		request.setFlagMail(false);
		// request.setFechaPrevista(null);
		// request.setFechaPeticion(null);
		request.setAplicacion("LPUACCIONES");

		LpuEncolarResponse response = (LpuEncolarResponse) getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}
}*/