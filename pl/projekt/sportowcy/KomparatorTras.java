package pl.projekt.sportowcy;

import pl.projekt.osrodek.Trasa;

@FunctionalInterface
public interface KomparatorTras {
    int porownaj(Trasa trasa1, int odl1, Trasa trasa2, int odl2);
}
