package uk.ac.cam.ch.wwmm.opsin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import uk.ac.cam.ch.wwmm.opsin.OpsinResult.OPSIN_RESULT_STATUS;

public class CedrolConfigurationTest {

	@ParameterizedTest
	@ValueSource(strings = {"cedrol", "cedryl acetate", "cedryl formate", "cedryl methyl ether"})
	public void cedrolDerivativesCarryAllFiveStereocentresOfNaturalCedrol(String name) {
		//(+)-cedrol, CAS 77-53-2: (1S,2R,5S,7R,8R)-2,6,6,8-tetramethyltricyclo[5.3.1.0^1,5]undecan-8-ol
		OpsinResult result = NameToStructure.getInstance().parseChemicalName(name);
		assertEquals(OPSIN_RESULT_STATUS.SUCCESS, result.getStatus(), result.getMessage());
		int definedCentres = 0;
		for (Atom a : result.getStructure().getAtomList()) {
			if (a.getAtomParity() != null) {
				definedCentres++;
			}
		}
		assertEquals(5, definedCentres);
	}
}
