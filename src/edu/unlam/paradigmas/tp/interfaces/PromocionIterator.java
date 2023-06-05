package edu.unlam.paradigmas.tp.interfaces;

import edu.unlam.paradigmas.tp.entidades.Promocion;

public interface PromocionIterator {

	public boolean hasNext();

	public Promocion next();

	public void reset();
}
