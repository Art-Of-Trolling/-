/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2020 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.init;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import reborncore.common.fluid.*;
import techreborn.TechReborn;

import java.util.Locale;

public enum ModFluids {
	ACTINIUM,
	ALUMINUM,
	AMERICIUM,
	ANTIMONY,
	ARGON,
	ARSENIC,
	ASTATINE,
	BARIUM,
	BERKELIUM,
	BERYL,
	BERYLLIUM,
	BIOFUEL,
	BISMUTH,
	BOHRIUM,
	BORON,
	CADMIUM,
	CALCIUM,
	CALCIUM_CARBONATE,
	CALIFORNIUM,
	CARBON,
	CARBON_FIBER,
	CERIUM,
	CESIUM,
	CHLORINE,
	CHLORITE,
	CHROMIUM,
	COBALT,
	COMPRESSED_AIR,
	COPERNICIUM,
	COPPER,
	CURIUM,
	DARMSTADTIUM,
	DEUTERIUM,
	DIESEL,
	DUBNIUM,
	DYSPROSIUM,
	EINSTEINIUM,
	ELECTROLYZED_WATER,
	ERBIUM,
	EUROPIUM,
	FERMIUM,
	FLEROVIUM,
	FLUORINE,
	FRANCIUM,
	GADOLINIUM,
	GALLIUM,
	GERMANIUM,
	GLYCERYL,
	HAFNIUM,
	HASSIUM,
	HELIUM,
	HELIUM3,
	HELIUMPLASMA,
	HOLMIUM,
	HYDROGEN,
	INDIUM,
	IRIDIUM,
	KRYPTON,
	LANTHANUM,
	LAWRENCIUM,
	LEAD,
	LIQUID_AIR,
	LITHIUM,
	LIVERMORIUM,
	LUTETIUM,
	MAGNESIUM,
	MANGANESE,
	MEITNERIUM,
	MENDELEVIUM,
	MERCURY,
	METHANE,
	MOLYBDENUM,
	MOSCOVIUM,
	NEODYMIUM,
	NEON,
	NEPTUNIUM,
	NICKEL,
	NIHONIUM,
	NIOBIUM,
	NITROCOAL_FUEL,
	NITROFUEL,
	NITROGEN,
	NITROGEN_DIOXIDE,
	NITRO_CARBON,
	NITRO_DIESEL,
	NOBELIUM,
	OGANESSON,
	OIL,
	OSMIUM,
	PALLADIUM,
	PLATINUM,
	PLUTONIUM,
	POLONIUM,
	POTASSIUM,
	PRASEODYMIUM,
	PROMETHIUM,
	PROTACTINIUM,
	RADIUM,
	RADON,
	RHENIUM,
	RHODIUM,
	ROENTGENIUM,
	RUBIDIUM,
	RUTHENIUM,
	RUTHERFORDIUM,
	SAMARIUM,
	SCANDIUM,
	SEABORGIUM,
	SELENIUM,
	SILICON,
	SILVER,
	SODIUM,
	SODIUM_HYDROXIDE,
	SODIUM_PERSULFATE,
	SODIUM_SULFIDE,
	STRONTIUM,
	SULFUR,
	SULFURIC_ACID,
	TANTALUM,
	TECHNETIUM,
	TELLURIUM,
	TENNESSINE,
	TERBIUM,
	THALLIUM,
	THORIUM,
	THULIUM,
	TIN,
	TITANIUM,
	TRITIUM,
	TUNGSTEN,
	URANIUM,
	VANADIUM,
	WOLFRAMIUM,
	XENON,
	YTTERBIUM,
	YTTRIUM,
	ZINC,
	ZIRCONIUM;

	private RebornFluid stillFluid;
	private RebornFluid flowingFluid;

	private RebornFluidBlock block;
	private RebornBucketItem bucket;
	private final Identifier identifier;

	ModFluids() {
		this.identifier = new Identifier(TechReborn.MOD_ID, this.toString().toLowerCase(Locale.ROOT));

		FluidSettings fluidSettings = FluidSettings.create();

		Identifier texture = new Identifier(TechReborn.MOD_ID, "block/fluids/" + this.toString().toLowerCase(Locale.ROOT) + "_flowing");

		fluidSettings.setStillTexture(texture);
		fluidSettings.setFlowingTexture(texture);

		stillFluid = new RebornFluid(true, fluidSettings, () -> block, () -> bucket, () -> flowingFluid, () -> stillFluid) {
		};
		flowingFluid = new RebornFluid(false, fluidSettings, () -> block, () -> bucket, () -> flowingFluid, () -> stillFluid) {
		};

		block = new RebornFluidBlock(stillFluid, FabricBlockSettings.of(Material.WATER).noCollision().hardness(100.0F).dropsNothing());
		bucket = new RebornBucketItem(stillFluid, new Item.Settings().group(TechReborn.ITEMGROUP).recipeRemainder(Items.BUCKET).maxCount(1));
	}

	public void register() {
		RebornFluidManager.register(stillFluid, identifier);
		RebornFluidManager.register(flowingFluid, new Identifier(TechReborn.MOD_ID, identifier.getPath() + "_flowing"));

		Registry.register(Registry.BLOCK, identifier, block);
		Registry.register(Registry.ITEM, new Identifier(TechReborn.MOD_ID, identifier.getPath() + "_bucket"), bucket);
	}

	public RebornFluid getFluid() {
		return stillFluid;
	}

	public RebornFluid getFlowingFluid() {
		return flowingFluid;
	}

	public RebornFluidBlock getBlock() {
		return block;
	}

	public Identifier getIdentifier() {
		return identifier;
	}
}