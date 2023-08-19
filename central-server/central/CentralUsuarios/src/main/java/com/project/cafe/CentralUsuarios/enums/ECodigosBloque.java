package com.project.cafe.CentralUsuarios.enums;

public enum ECodigosBloque {
	A("A"),
	B("B"),
	C("C"),
	D("D"),
	E("E"),
	F("F"),
	G("G"),
	H("H"),
	I("I"),
	J("J"),
	K("K"),
	L("L"),
	M("M"),
	N("N"),
	Ñ("Ñ"),
	O("O"),
	P("P"),
	Q("Q"),
	R("R"),
	S("S"),
	T("T"),
	U("U"),
	V("V"),
	W("W"),
	X("X");

	private final String nombre;

	private ECodigosBloque(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
