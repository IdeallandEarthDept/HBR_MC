local outFile = nil;
local modName = "hbr_mc";
local blockName = "grid_normal";

local function GenModelBlockItem(spName)
	if spName == nil then
		spName = blockName;
	end

	local path = string.format("src\\main\\resources\\assets\\%s\\models\\item\\%s.json", modName, blockName);
	outFile = io.open(path,"w");
	outFile:write('{\n');
	local content = string.format("\t\"parent\": \"%s:block/%s\"\n", modName, spName);
	outFile:write(content);
	outFile:write('}\n');
	outFile:close();
end

local function GenModelBlock()
	local path = string.format("src\\main\\resources\\assets\\%s\\models\\block\\%s.json", modName, blockName);
	outFile = io.open(path,"w");
	outFile:write('{\n');
	outFile:write('\t\"parent\": \"block/cube_all\",\n');
	outFile:write('\t\"textures\": {\n');
	local content = string.format("\t\t\"all\": \"%s:blocks/%s\"\n", modName,blockName );
	outFile:write(content);
	outFile:write('\t}\n');
	outFile:write('}\n');
	outFile:close();
end

local filename_stairs_original = { "stairs", "inner_stairs", "outer_stairs" }
local filename_stairs_postfix = { "", "_inner", "_outer" }

local filename_rail_postfix = { "_curved", "_flat", "_raised_ne", "_raised_sw" }
local picName_rail_postfix = { "_turned", "", "", "" }

local function GenModelBlockRail(blockName, picName)
	for i = 1, 4 do
		local path = string.format("src\\main\\resources\\assets\\%s\\models\\block\\%s.json", modName, blockName .. filename_rail_postfix[i]);
		outFile = io.open(path, "w");
		outFile:write('{\n');
		outFile:write(string.format('\t\"parent\": \"block/%s\",\n', "rail" .. filename_rail_postfix[i]));
		outFile:write('\t\"textures\": {\n');
		local content = string.format("\t\t\"rail\": \"%s:blocks/%s\"\n", modName, picName .. picName_rail_postfix[i]);
		outFile:write(content);
		outFile:write('\t}\n');
		outFile:write('}\n');
		outFile:close();
	end
end

--sometimes you may wish to create stairs with
local function GenModelBlockStairs(picName, picMod)
	if picMod == nil then
		picMod = modName
	end

	for i = 1, 3 do
		local path = string.format("src\\main\\resources\\assets\\%s\\models\\block\\%s.json", modName, blockName .. filename_stairs_postfix[i]);
		outFile = io.open(path, "w");
		outFile:write('{\n');
		outFile:write(string.format('\t\"parent\": \"block/%s\",\n', filename_stairs_original[i]));
		outFile:write('\t\"textures\": {\n');
		local content = string.format("\t\t\"bottom\": \"%s:blocks/%s\",\n", picMod, picName);
		outFile:write(content);
		content = string.format("\t\t\"top\": \"%s:blocks/%s\",\n", picMod, picName);
		outFile:write(content);
		content = string.format("\t\t\"side\": \"%s:blocks/%s\"\n", picMod, picName);
		outFile:write(content);
		outFile:write('\t}\n');
		outFile:write('}\n');
		outFile:close();
	end
end

local function GenBlockState()
	local path = string.format("src\\main\\resources\\assets\\%s\\blockstates\\%s.json", modName, blockName);
	outFile = io.open(path,"w");
	outFile:write('{\n');
	outFile:write('\t\"variants\": {\n');
	local content = string.format("\t\t\"normal\": { \"model\": \"%s:%s\" }\n", modName,blockName );
	outFile:write(content);
	outFile:write('\t}\n');
	outFile:write('}\n');
	outFile:close();
end

local function GenBlockStateStairs()
	local path = string.format("src\\main\\resources\\assets\\%s\\blockstates\\%s.json", modName, blockName);
	outFile = io.open(path, "w");

	local content = '{\n"variants":{\n"facing=east,half=bottom,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]"\n},\n"facing=west,half=bottom,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"y":180,\n"uvlock":true\n},\n"facing=south,half=bottom,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"y":90,\n"uvlock":true\n},\n"facing=north,half=bottom,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"y":270,\n"uvlock":true\n},\n"facing=east,half=bottom,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer"\n},\n"facing=west,half=bottom,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":180,\n"uvlock":true\n},\n"facing=south,half=bottom,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":90,\n"uvlock":true\n},\n"facing=north,half=bottom,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":270,\n"uvlock":true\n},\n"facing=east,half=bottom,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":270,\n"uvlock":true\n},\n"facing=west,half=bottom,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":90,\n"uvlock":true\n},\n"facing=south,half=bottom,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer"\n},\n"facing=north,half=bottom,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"y":180,\n"uvlock":true\n},\n"facing=east,half=bottom,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner"\n},\n"facing=west,half=bottom,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":180,\n"uvlock":true\n},\n"facing=south,half=bottom,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":90,\n"uvlock":true\n},\n"facing=north,half=bottom,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":270,\n"uvlock":true\n},\n"facing=east,half=bottom,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":270,\n"uvlock":true\n},\n"facing=west,half=bottom,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":90,\n"uvlock":true\n},\n"facing=south,half=bottom,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner"\n},\n"facing=north,half=bottom,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"y":180,\n"uvlock":true\n},\n"facing=east,half=top,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"x":180,\n"uvlock":true\n},\n"facing=west,half=top,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"x":180,\n"y":180,\n"uvlock":true\n},\n"facing=south,half=top,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"x":180,\n"y":90,\n"uvlock":true\n},\n"facing=north,half=top,shape=straight":{\n"model":"[MODID]:[BLOCK_ID]",\n"x":180,\n"y":270,\n"uvlock":true\n},\n"facing=east,half=top,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":90,\n"uvlock":true\n},\n"facing=west,half=top,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":270,\n"uvlock":true\n},\n"facing=south,half=top,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":180,\n"uvlock":true\n},\n"facing=north,half=top,shape=outer_right":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"uvlock":true\n},\n"facing=east,half=top,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"uvlock":true\n},\n"facing=west,half=top,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":180,\n"uvlock":true\n},\n"facing=south,half=top,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":90,\n"uvlock":true\n},\n"facing=north,half=top,shape=outer_left":{\n"model":"[MODID]:[BLOCK_ID]_outer",\n"x":180,\n"y":270,\n"uvlock":true\n},\n"facing=east,half=top,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":90,\n"uvlock":true\n},\n"facing=west,half=top,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":270,\n"uvlock":true\n},\n"facing=south,half=top,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":180,\n"uvlock":true\n},\n"facing=north,half=top,shape=inner_right":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"uvlock":true\n},\n"facing=east,half=top,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"uvlock":true\n},\n"facing=west,half=top,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":180,\n"uvlock":true\n},\n"facing=south,half=top,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":90,\n"uvlock":true\n},\n"facing=north,half=top,shape=inner_left":{\n"model":"[MODID]:[BLOCK_ID]_inner",\n"x":180,\n"y":270,\n"uvlock":true\n}\n}\n}\n';
	content = string.gsub(content, "%[MODID%]", modName)
	content = string.gsub(content, "%[BLOCK_ID%]", blockName)

	outFile:write(content);

	outFile:close();
end

local function GenBlockStateRail(blockName)
	local path = string.format("src\\main\\resources\\assets\\%s\\blockstates\\%s.json", modName, blockName);
	outFile = io.open(path, "w");


	local content = '{\n    "variants": {\n        "shape=north_south": { "model": "[MODID]:[BLOCK_ID]_flat" },\n        "shape=east_west": { "model": "[MODID]:[BLOCK_ID]_flat", "y": 90 },\n        "shape=ascending_east": { "model": "[MODID]:[BLOCK_ID]_raised_ne", "y": 90 },\n        "shape=ascending_west": { "model": "[MODID]:[BLOCK_ID]_raised_sw", "y": 90 },\n        "shape=ascending_north": { "model": "[MODID]:[BLOCK_ID]_raised_ne" },\n        "shape=ascending_south": { "model": "[MODID]:[BLOCK_ID]_raised_sw" },\n        "shape=south_east": { "model": "[MODID]:[BLOCK_ID]_curved" },\n        "shape=south_west": { "model": "[MODID]:[BLOCK_ID]_curved", "y": 90 },\n        "shape=north_west": { "model": "[MODID]:[BLOCK_ID]_curved", "y": 180 },\n        "shape=north_east": { "model": "[MODID]:[BLOCK_ID]_curved", "y": 270 }\n    }\n}\n';
	content = string.gsub(content, "%[MODID%]", modName)
	content = string.gsub(content, "%[BLOCK_ID%]", blockName)

	outFile:write(content);

	outFile:close();
end

function GenBlock(_blockName)
	blockName = _blockName;
	print("Creating:"..blockName)
	GenModelBlockItem();
	GenModelBlock();
	GenBlockState();
end

function GenBlockStairs(_blockName, _picName)
	blockName = _blockName;
	print("Creating Stairs:" .. blockName)
	GenModelBlockItem();
	GenModelBlockStairs(_picName);
	GenBlockStateStairs();
end

function GenBlockRail(_blockName, _picName)
	if _picName == nil then
		_picName = _blockName;
	end
	blockName = _blockName;
	print("Creating Stairs:" .. _blockName)
	GenItem(nil, _blockName, true, _blockName);
	GenModelBlockRail(_blockName, _picName);
	GenBlockStateRail(_blockName);
end

function GenItem(_typeName, _itemName, isBlock, picName)
	if picName == nil then picName = _itemName end
	if _typeName == nil then _typeName = "" end

	print("Creating:" .. (_typeName or "") .. " " .. _itemName)
	local path = string.format("src\\main\\resources\\assets\\%s\\models\\item\\%s.json", modName, _itemName);
	outFile = io.open(path,"w");

	local content;
	if isBlock then
		content = string.format('{"parent": "item/handheld","textures": {"layer0":"%s:blocks/%s"}}\n', modName, picName);
	else
		content = string.format('{"parent": "item/handheld","textures": {"layer0":"%s:items/%s/%s"}}\n', modName, _typeName, _itemName);
	end

	outFile:write(content);

	outFile:close();
end

function GenTCG(_rarity, _itemName)
	print("Creating TCG:".._rarity.." ".._itemName)
	local path = string.format("src\\main\\resources\\assets\\%s\\models\\item\\%s.json", modName, "tcg_".._itemName);
	outFile = io.open(path,"w");

	local content = string.format('{"parent": "item/handheld","textures": {"layer0":"%s:tcg/tcgbg_%s","layer1":"%s:tcg/%s"}}\n', modName, _rarity, modName, _itemName );
	outFile:write(content);

	outFile:close();
end
