package edu.unlam.paradigmas.tp.interfaces;

import edu.unlam.paradigmas.tp.entidades.Atraccion;

public interface AtraccionIterator {

	public boolean hasNext();

	public Atraccion next();

	public void reset();

}
