package com.project.cafe.CentralUsuarios.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestSendEMailDTO implements Serializable {

	private static final long serialVersionUID = -7979403290212524827L;

	private String desde;

	private List<String> para;

	private String asunto;

	private List<String> parametros;

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public List<String> getPara() {
		return para;
	}

	public void setPara(List<String> para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public List<String> getParametros() {
		return parametros;
	}

	public void setParametros(List<String> parametros) {
		this.parametros = parametros;
	}

	public Map<String, Object> getMapaDeLista() {
		Map<String, Object> result = new HashMap<>();
		if (this.parametros != null && !this.parametros.isEmpty()) {
			for (String keyPar : this.parametros) {
				String key = keyPar.split("\\|")[0];
				String value = keyPar.split("\\|")[1];
				if (!result.containsKey(key)) {
					result.put(key, value);
				}
			}
		}

		return result;
	}

}
