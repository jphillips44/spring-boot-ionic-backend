package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {
 	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente tpCli : TipoCliente.values()) {
			if(cod.equals(tpCli.getCodigo())) {
				return tpCli;
			}
		}
		
		throw new IllegalArgumentException("Id inválido! id= " + cod);
		
	}
		
}
