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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SociedadAreaTB getSociedadArea() {
		return sociedadArea;
	}

	public void setSociedadArea(SociedadAreaTB sociedadArea) {
		this.sociedadArea = sociedadArea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CajaTB getCaja() {
		return caja;
	}

	public void setCaja(CajaTB caja) {
		this.caja = caja;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public TipoUDTB getTipoDocumental() {
		return tipoDocumental;
	}

	public void setTipoDocumental(TipoUDTB tipoDocumental) {
		this.tipoDocumental = tipoDocumental;
	}

	public Long getConsecutivoIni() {
		return consecutivoIni;
	}

	public void setConsecutivoIni(Long consecutivoIni) {
		this.consecutivoIni = consecutivoIni;
	}

	public Long getConsecutivoFin() {
		return consecutivoFin;
	}

	public void setConsecutivoFin(Long consecutivoFin) {
		this.consecutivoFin = consecutivoFin;
	}

	public ContenedorUDTB getContenedor() {
		return contenedor;
	}

	public void setContenedor(ContenedorUDTB contenedor) {
		this.contenedor = contenedor;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaRecibe() {
		return fechaRecibe;
	}

	public void setFechaRecibe(Date fechaRecibe) {
		this.fechaRecibe = fechaRecibe;
	}
	
}
