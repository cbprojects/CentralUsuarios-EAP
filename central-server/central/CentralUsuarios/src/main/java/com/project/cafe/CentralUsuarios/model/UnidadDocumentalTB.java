package com.project.cafe.CentralUsuarios.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gdo_unidad_documental_tb")
public class UnidadDocumentalTB extends BaseEntidadTB implements Serializable {
	
	private static final long serialVersionUID = 5386375061877745298L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "und_id", nullable = false)
	private long id;

	@NotNull
	@Column(name = "und_codigo", nullable = false, length = 30)
	private String codigo;
	
	@NotNull
	@Column(name = "und_nombre", nullable = false, length = 50)
	private String nombre;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private SociedadAreaTB sociedadArea;
	
	@Column(name = "und_descripcion", nullable = true, length = 500)
	private String descripcion;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private CajaTB caja;
	
	@NotNull
	@Column(name = "und_codBarra", nullable = false, length = 128)
	private String codigoBarra;
	
	@Column(name = "und_qr", nullable = true, length = 128)
	private String qr;
	
	@Column(name = "und_ruta_archivo", nullable = true, length = 128)
	private String rutaArchivo;
	
	@Lob
	@Column(name = "und_nombre_archivos", nullable = true)
	private String nombreArchivos;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private TipoUDTB tipoDocumental;
	
	@Column(name = "und_consecutivo_ini", nullable = true)
	private Long consecutivoIni;
	
	@Column(name = "und_consecutivo_fin", nullable = true)
	private Long consecutivoFin;
	
	@NotNull
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private ContenedorUDTB contenedor;
	
	@Column(name = "und_fecha_ini", nullable = true)
	private Date fechaIni;
	
	@Column(name = "und_fecha_fin", nullable = true)
	private Date fechaFin;
	
	@NotNull
	@Column(name = "und_fecha_recibe", nullable = false)
	private Date fechaRecibe;
	
	
	@Column(name = "und_cajaRecibido", nullable = true, length = 128)
	private String cajaRecibido;

	
}
